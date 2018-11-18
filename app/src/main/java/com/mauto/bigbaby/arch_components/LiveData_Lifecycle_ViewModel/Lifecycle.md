### Lifecycle
Lifecycles组件的作用，可以不负责任的用一句话总结：用来监听FragmentActivity或者Fragment的生命周期。

#### 一 使用方法
*** QUESTION_0 : Lifecycles解决了什么痛点呢？ ***

&emsp;&emsp;假如有一个使用MVP构建的项目，我需要一个Presenter，但是这个Presenter必须能够监听到FragmentActivity或者Fragment的生命周期，并根据不同的周期作出不同的逻辑操作。那么再不使用Lifecycles组件的情况下如何处理？  
&emsp;&emsp;我们可能会这样写：  
&emsp;&emsp;先定义一个IPresenter，包含全部与FragmentActivity生命周期函数相对应的方法，类图是这样的  
<div align=center>![avatar](/res/arch_lifecycle_ipresenter.png)     
