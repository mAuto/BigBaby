#### DiffUtil  

    DiffUtil是RecyclerView库下的一个使用频率较高的用来实现增量更新的工具。
    这里增量更新的概念容易产生误解。
    这里的增量更新指的是，向列表中插入那些存在于新数据源而不存在于旧数据源的数据，但是那些存在于旧数据源而不存在与新数据源的数据(item)将会被remove掉。
    所以，DiffUtil的增量更新实现的不是那种可以叠加的增量更新，而是会用新数据覆盖旧数据，只不过只会更新那些只存在于新数据源的数据。

##### 1 使用方法
&emsp;&emsp;DiffUtil的基本使用非常简单。只需要实现一个Callback就可以了：
- DiffCallback.java

```java
public class DiffCallback extends DiffUtil.Callback {

    private List<GankBean> mOldData, mNewData;

    public DiffCallback(@NonNull List<GankBean> oldData, @NonNull List<GankBean> newData) {
        mNewData = newData;
        mOldData = oldData;
    }

    @Override
    public int getOldListSize() {
        return mOldData == null ? 0 : mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData == null ? 0 : mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return TextUtils.equals(mOldData.get(i)._id, mNewData.get(i1)._id);
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return mOldData.get(i).isEqualsTo(mNewData.get(i1));
    }
}
```

&emsp;&emsp;然后在获取到新数据后这样处理：

```java
private void fetchData() {
        DataRepository.fetchDataFromRemote(new RandomDataOp(), new RepositoryAction() {
            @Override
            public void onAction(ResponseModel model) {
                if (model instanceof LoadingModel) {
                    mPbLoading.setVisibility(View.VISIBLE);
                } else if (model instanceof ErrModel) {
                    mPbLoading.setVisibility(View.GONE);
                } else {
                    mPbLoading.setVisibility(View.GONE);

                    // 这里是最重要的
                    // ****************************************************************
                    ArrayList<GankBean> newData = ((RandomResponseBody) model.resultBody).results;
                    ArrayList<GankBean> oldData = (ArrayList<GankBean>) mAdapter.getOriginalData();

                    // calculateDiff方法用来对比新旧数据，并获得一个result，包含哪些item需要remove，那些item需要insert
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(oldData, newData), true);

                    // 将新数据fill到adapter中替换旧数据，是替换旧数据而不是添加到就数据后边(这里有个疑问下边会讲)。
                    mAdapter.fillData(newData);

                    // 根据diffResult分发更新事件，该insert的insert，该remove的remove。
                    diffResult.dispatchUpdatesTo(mAdapter);
                    // *****************************************************************
                }
            }
        });
    }
```

&emsp;&emsp;这样就可以简单的使用DiffUtil来实现一个增量更新的case。

> 上面有个疑问，为什么要用新数据替换旧数据，而不是追加到旧数据后边(或者某个位置)?
> ```java
> public void fillData(List<GankBean> data) {
>          if (data == null || data.size() == 0)
>              return;
>
>          if (mData == null)
>              mData = new ArrayList<>();
>
>          mData.clear();// 这里注释掉会有什么区别？
>          mData.addAll(data);
>      }
> ```
>
> ![avatar](/res/lib_recycler_diffutil_0.png)
>
> ![avatar](/res/lib_recycler_diffutil_1.png)

##### 2 源码及原理(RecyclerView lib的源码分析有优先级不高，熟练掌握使用既可以了，留待时间充裕时分析)
