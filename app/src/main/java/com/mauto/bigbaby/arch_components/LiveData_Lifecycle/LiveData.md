## LiveData
LiveData是一个抽象类，源码不多，算上注释440行上下。
#### LiveData的优点

#### 重要的方法：
- postValue(T postValue):void

如果让我写同等作用的代码，绝对不止这些行数，也不是这个实现逻辑。
```
private void dispatchingValue(@Nullable ObserverWrapper initiator) {
    if (mDispatchingValue) {
        mDispatchInvalidated = true;
        return;
    }
    mDispatchingValue = true;
    do {
        mDispatchInvalidated = false;
        if (initiator != null) {
            considerNotify(initiator);
            initiator = null;
        } else {
            for (Iterator<Map.Entry<Observer<T>, ObserverWrapper>> iterator =
                    mObservers.iteratorWithAdditions(); iterator.hasNext(); ) {
                considerNotify(iterator.next().getValue());
                if (mDispatchInvalidated) {
                    break;
                }
            }
        }
    } while (mDispatchInvalidated);
    mDispatchingValue = false;
}
```
每个LiveData实例都合一个固定的LifeCycleOwner绑定，也就是说在yigeLiveData中不允许添加不同owner的Observer。
```
public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
    if (owner.getLifecycle().getCurrentState() == DESTROYED) {
        // ignore
        return;
    }
    LifecycleBoundObserver wrapper = new LifecycleBoundObserver(owner, observer);
    ObserverWrapper existing = mObservers.putIfAbsent(observer, wrapper);
    //
    if (existing != null && !existing.isAttachedTo(owner)) {
        throw new IllegalArgumentException("Cannot add the same observer"
                + " with different lifecycles");
    }
    if (existing != null) {
        return;
    }
    owner.getLifecycle().addObserver(wrapper);
}
```
#### LifeCycleRegistry持有LiveData的observer，为什么？
感知生命周期的不是LiveData，是LifeCycle和LifeCycleOwner。LiveData只是相当于一个观察者模式的管理器而已。如果LiveData没有与LifeCycle有关系的话，是不能实现根据生命周期状态callback的。
#### LifeCycle是如何感知生命周期的？
无论是Fragment还是Activity，肯定会在他们各自的生命周期调用改变生命周期状态的方法。Fragment/ReportFragment
