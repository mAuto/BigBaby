## LiveData
LiveData是一个抽象类，源码不多，算上注释440行上下。
#### LiveData的优点

#### LifeCycleRegistry持有LiveData的observer，为什么？
感知生命周期的不是LiveData，是LifeCycle和LifeCycleOwner。LiveData只是相当于一个观察者模式的管理器而已。如果LiveData没有与LifeCycle有关系的话，是不能实现根据生命周期状态callback的。
#### LifeCycle是如何感知生命周期的？
无论是Fragment还是Activity，肯定会在他们各自的生命周期调用改变生命周期状态的方法。Fragment/ReportFragment
