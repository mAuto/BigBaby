#### AsyncListUtil  

&emsp;&emsp;AsyncListUtil是RecyclerView库中的一个单线程分页异歩*(获取数据与初始化UI之间异步，所有页的数据获取以及对应的UI更新，是同步的)* 加载工具，主要用于从硬盘(比如数据库)中读取数据，不推荐用于从网络获取数据的更新操作。与RecyclerView的Adapter搭配使用。  

>为什么不推荐用于从网络获取数据？  
>1) 单线程。所有页的数据请求都会放到一个线程中同步执行，然后同步更新UI，如果用在获取网络请求数据的时候，每页的请求时间就会叠加，产生一个理论上会很长的延迟。  
>2) AsyncListUtil本身的局限性，或者说其所针对解决的问题决定的。AsyncListUtil更新数据之前，列表的长度往往是已经确定的。通用的更新网络的交互是上拉加载更多或者下拉刷新，这种分页方式是不定长的; 而加载数据库数据的列表，通常是一次性获取所有数据的cursor，然后一次性全部加载出来*(也可以填充到一个刷新组件中，但是底层通常都是一次性获取所有数据)* ，这种方式是定长的。关于这个原因的具体分析会放到源码解析模块去进一步阐述。    
>>慎重点：AsyncListUtil内部有提供一个方法(refresh())，可以主动的刷新列表的长度，但是这个方法会强制清除掉已经加载完成的所有UI以及底层的数据，而使当前视界范围内的item数据重新获取，UI重新加载，已经加载好的视界外的UI同样会被重置，需要重新加载数据更新UI。基于这一点，这个方法无论是用在数据库加载还是网络加载都不大合适，会造成资源和性能的浪费*(但是不排出某些特殊的场景恰好需要这一特性)* 。这一点也会放到源码解析模块中进一步阐述。


##### 1 使用方法   
&emsp;&emsp;AsyncListUtil的使用需要四部分配合：DataCallback，用来处理数据相关的逻辑(返回定长，以及填充数据等)，;ViewCallback，用来处理UI相关的操作(书信整个列表，刷新单个item等);AsyncLisUtil，用来监听列表的滑动时间，得到当前的视界范围，判断是否需要填充数据并更新UI;Adapter，adapter的数据直接从AsyncListUtil中获得，如果为null的话代表当前数据还没有加载完成，可以用一个默认UI填充，填充完毕后，AsyncListUtil会通知ViewCallback找到需要更新item，并更新它。   
&emsp;&emsp;所以，使用一个AsyncListUtil至少需要四步：  
- 实现一个DataCallback   
```
public class BigDataCallback extends AsyncListUtil.DataCallback<String> {
    @Override
    public int refreshData() {
        // 返回列表长度
        /**
         * 调用栈：AsyncListUtil construction method
         * -> AsyncListUtil.refresh()
         * -> ThreadUtil.BackgroundCallback.refresh()
         * -> AsyncListUtil.DataCallback.refreshData
         * */
        return 100;
    }

    @Override
    public void fillData(String[] data, int startPosition, int itemCount) {

        // 填充数据
        for (int i=0;i<itemCount;i++) {
            data[i] = "Item: " + (startPosition + i + 1);
        }

        // 暂停一秒模拟取数据库耗时
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {}
    }
}
```
- 实现一个ViewCallback   
```
public class BigViewCallback extends AsyncListUtil.ViewCallback {

    private RecyclerView mView;

    public BigViewCallback(RecyclerView view) {
        mView = view;
    }

    // 确定当前视界范围
    @Override
    public void getItemRangeInto(int[] outRange) {
        LinearLayoutManager manager = (LinearLayoutManager) mView.getLayoutManager();
        outRange[0] = manager.findFirstVisibleItemPosition();
        outRange[1] = manager.findLastVisibleItemPosition();
    }

    // 刷新整个列表
    @Override
    public void onDataRefresh() {
        mView.getAdapter().notifyDataSetChanged();
    }

    // 刷新单个item
    @Override
    public void onItemLoaded(int position) {
        mView.getAdapter().notifyItemChanged(position);
    }
}
```
- 实现一个AsyncListUtil   
```
public class BigAsyncListUtil extends AsyncListUtil<String> {

    // 分页大小，每页20条数据
    private static int TILE_SIZE = 20;

    public BigAsyncListUtil(RecyclerView recyclerView) {
        super(String.class, TILE_SIZE, new BigDataCallback(), new BigViewCallback(recyclerView));

        if (recyclerView != null) {
            // 监听RecyclerView的滑动事件，即使对其视界范围做出响应
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    // 更新当前可见范围
                    onRangeChanged();
                }
            });
        }
    }
}
```
- 将实例化的AsyncListUtil放到Adapter中   
```
@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = mAsyncListUtil.getItem(position);
        // 可能为null，没有填充完毕即为null
        if (TextUtils.isEmpty(title))
            holder.tvTitle.setText("loading...");
        else
            holder.tvTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        // 获取列表长度
        return mAsyncListUtil.getItemCount();
    }
```   

&emsp;&emsp;之后再将Adapter添加到列表中，及完成对AsyncListUtil的使用。   

<div align=center>![avatar](/res/async_effect.gif)</div>    


##### 2 源码及原理
