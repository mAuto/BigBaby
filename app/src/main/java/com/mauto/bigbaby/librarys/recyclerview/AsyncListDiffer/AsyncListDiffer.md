### AsyncListDiffer
&emsp;&emsp;AsyncListDiffer其实是对DiffUtil的一次简洁的封装，所以这里只写封装的部分，不了解Diffutil的话建议看过[DiffUtil](https://github.com/mAuto/BigBaby/blob/master/app/src/main/java/com/mauto/bigbaby/librarys/recyclerview/DiffUtil/DiffUtil.md)再来了解关于AsyncListDiffer的内容。

#### 1 使用方法
##### 1.1 核心类以及方法
&emsp;&emsp;AsyncListDiffer只是对Diffutil的再封装，所以核心类与方法基本与DiffUtil差不多。
- DiffUtil.ItemCallback: 定义新旧数据对比计算规则，相比于Diffutil.Callback，ItemCallback只提供item的对比，而不是Callback通过提供item的索引，来获取item进行对比。
- AsyncDifferConfig: 是一个包含ItemCallback的配置项，主要用来配置计算线程(子线程)和主线程(实际上是一个包含main looper的handler)。
  - AsyncDifferConfig.Builder: AsyncDifferConfig的builder方法，除了生成一个AsyncDifferConfig之外，还要初始化主线程handler和后台线程(线程池)。
- AsyncListDiffer: 持有新旧数据，计算差异的主体类，封住了DiffUtil的一套逻辑。
  - submitList: 注入新数据集合。
  - getCurrentList： 获取当前数据集合。
> - AsyncDifferConfig中的主线程配置部分
> ```java
> private static class MainThreadExecutor implements Executor {
>            final Handler mHandler = new Handler(Looper.getMainLooper());
>            @Override
>            public void execute(@NonNull Runnable command) {
>                mHandler.post(command);
>            }
>        }
> ```
> - AsyncDifferConfig中的后台线程的配置部分
> ```java
> public AsyncDifferConfig<T> build() {
>            //.............
>            if (mBackgroundThreadExecutor == null) {
>                synchronized (sExecutorLock) {
>                    if (sDiffExecutor == null) {
>                        // 一个固定数量的线程池
>                        sDiffExecutor = Executors.newFixedThreadPool(2);
>                    }
>                }
>                mBackgroundThreadExecutor = sDiffExecutor;
>            }
>            //..............
>        }
> ```

##### 1.2 具体使用方法
&emsp;&emsp;从Diffutil的用法中就不难看出，这类工具的整体思路就是，定义一个包含计算规则的callback，可以校验两个item是不是同一，内容是不是一样，甚至打包差量，然后讲这个回调封装进一个数据容器中，

&emsp;&emsp;AsyncListDiffer的使用方法也很简单，同样需要一个callback来定义计算规则。但是和DiffUtil不同，AsyncListDiffer的callback要继承自ItemCallback，而不是DiffUtil中使用的Callback。
- RandomDiffAdapter.java -> ItemCallback实例
```java
private DiffUtil.ItemCallback<GankBean> callback = new DiffUtil.ItemCallback<GankBean>() {
        @Override
        public boolean areItemsTheSame(GankBean oldItem, GankBean newItem) {
            return TextUtils.equals(oldItem._id, newItem._id);
        }

        @Override
        public boolean areContentsTheSame(GankBean oldItem, GankBean newItem) {
            return oldItem.isEqualsTo(newItem);
        }

        @Nullable
        @Override
        public Object getChangePayload(GankBean oldBean, GankBean newBean) {

            Bundle payload = null;


            if (!TextUtils.equals(oldBean.desc, newBean.desc)) {
                if (payload == null)
                    payload  = new Bundle();
                payload.putString("desc", newBean.desc);
            }
            if (!TextUtils.equals(oldBean.type, newBean.type)) {
                if (payload == null)
                    payload  = new Bundle();
                payload.putString("type", newBean.type);
            }

            if (!TextUtils.equals(oldBean.createAt, newBean.createAt)
                    || !TextUtils.equals(oldBean.publishedAt, newBean.publishedAt)
                    || !TextUtils.equals(oldBean.source, newBean.source)
                    || !TextUtils.equals(oldBean.url, newBean.url)
                    || !TextUtils.equals(oldBean.who, newBean.who)
                    || !TextUtils.equals(oldBean.used, newBean.used))
                if (payload == null)
                    payload  = new Bundle();

            return payload;
        }
    };
```
&emsp;&emsp;具体实现和前面DiffUtil的方法基本一致。然后生成一个AsyncListDiffer实例，用来计算新旧数据集和的差异。
- RandomDiffAdapter.java
```java
public class RandomDiffAdapter extends RecyclerView.Adapter<RandomDiffAdapter.ViewHolder> {

    private Context mContext;
    public RandomDiffAdapter(Context context) {
        mContext = context;

        mDiffer = new AsyncListDiffer<GankBean>(this, callback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.libs_recycler_item_layout, null, false));
    }

    // 与DiffUtil的代码逻辑一样，高效的局部绑定更新要在这个方法中直接消化掉，需要全部绑定更新的item要调用二参onBindViewHolder方法。
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        Bundle payload = null;
        if (payloads != null && payloads.size() > 0)
            payload = (Bundle) payloads.get(0);

        if (payload == null)
            super.onBindViewHolder(holder, position, payloads);
        else {
            if (holder != null) {
                String type = payload.getString("type");
                if (!TextUtils.isEmpty(type))
                    holder.tvTitle.setText(type);

                String desc = payload.getString("desc");
                if (!TextUtils.isEmpty(desc))
                    holder.tvDesc.setText(desc);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder != null) {
            GankBean bean = mDiffer.getCurrentList().get(position);

           // 具体的渲染逻辑
        }
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public void fillData(List<GankBean> data) {
        mDiffer.submitList(data);
    }

    public List<GankBean> getOriginalData() {
        return mDiffer.getCurrentList();
    }

    // ViewHolder

    /////////////////////////////////////////--> 18-12-14 下午9:45 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> async differ <-- ↓↓↓/////////////////////////////////////
    private AsyncListDiffer<GankBean> mDiffer;

    private DiffUtil.ItemCallback<GankBean> callback = new DiffUtil.ItemCallback<GankBean>() {
        // 计算规则的初始化代码，同上。
    };
    /////////////////////////////////////↑↑↑ --> async differ <-- ↑↑↑/////////////////////////////////////
}

```
&emsp;&emsp;到这里位置，就完成了AsyncListDiffer的使用，一个很简单的例子。
> 值得注意的是，RecyclerView库还提供了另外一个工具类---ListAdapter，来实现这一套流程，这个工具类是对Adapter和AsyncListDiffer的再封装，
只需要传入一个自定义的ItemCallback以及实现必要的渲染逻辑即可，使用方法相比AsyncListDiffer又简单很多，基本原理与AsyncListDiffer大同小异，不做赘述。

#### 2 重要的源码理解
&emsp;&emsp;AsyncListDiffer是对DiffUtil的封装，所以DiffUtil部分的源码不在这里涉及，这里只针对属于AsyncListDiffer的源码部分。

- AsyncDifferConfig:是初始化主线程Handler以及后台线程的配置类，同时持有计算规则回调---ItemCallback的实例。源码上面已写到。
- AsyncListDiffer最重要的是它的submitList方法，先来看源码：
```java
public void submitList(final List<T> newList) {
        if (newList == mList) {
            // nothing to do
            // 当新旧数据集同一时，不作任何处理
            return;
        }

        // incrementing generation means any currently-running diffs are discarded when they finish
        // 为了防止过度绘制的自增长型的标记
        final int runGeneration = ++mMaxScheduledGeneration;

        // fast simple remove all
        // 注入的新数据集合位null，将直接清空列表
        if (newList == null) {
            //noinspection ConstantConditions
            int countRemoved = mList.size();
            mList = null;
            mReadOnlyList = Collections.emptyList();
            // notify last, after list is updated
            mUpdateCallback.onRemoved(0, countRemoved);
            return;
        }

        // fast simple first insert
        // 如果是第一次注入数据，直接刷新UI，不做计算处理
        if (mList == null) {
            mList = newList;
            mReadOnlyList = Collections.unmodifiableList(newList);
            // notify last, after list is updated
            mUpdateCallback.onInserted(0, newList.size());
            return;
        }

        final List<T> oldList = mList;
        // 在后台线程中计算差值，和DiffUtil的使用是一样的
        mConfig.getBackgroundThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                    @Override
                    public int getOldListSize() {
                        return oldList.size();
                    }

                    @Override
                    public int getNewListSize() {
                        return newList.size();
                    }

                    @Override
                    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                        return mConfig.getDiffCallback().areItemsTheSame(
                                oldList.get(oldItemPosition), newList.get(newItemPosition));
                    }

                    @Override
                    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                        return mConfig.getDiffCallback().areContentsTheSame(
                                oldList.get(oldItemPosition), newList.get(newItemPosition));
                    }

                    @Nullable
                    @Override
                    // 局部绑定(差量更新) VS 完全绑定
                    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                        return mConfig.getDiffCallback().getChangePayload(
                                oldList.get(oldItemPosition), newList.get(newItemPosition));
                    }
                });

                mConfig.getMainThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        // 这里是后台线程，有这样一种场景：
                        // 在一次计算结果还没返回的时间段内，重复多次调用submitList方法，将会有多条线程计算新旧数据集和(实际上是个定长为2的线程池)，
                        // 然后每次计算技术之后都会通知adapter刷新，但是这是没有必要的，实际上只需要应用最近一次的结果就可以了，其他的结果完全可以drop。
                        // 避免了没有必要的刷新操作，提升交互体验。
                        if (mMaxScheduledGeneration == runGeneration) {
                            latchList(newList, result);
                        }
                    }
                });
            }
        });
    }
```
- AsyncListDiffer的getCurrentList方法有一个值得注意的点，这个方发返回的是mReadOnlyList，这个变量的初始状态是一个EmptyList类型的实例，
但是这种类型的实例强制转换成ArrayList是会报错的，所以在涉及到这个方法的地方，要么使用List，要么判断类型，如果是EmptyList就不转换成ArrayList。
