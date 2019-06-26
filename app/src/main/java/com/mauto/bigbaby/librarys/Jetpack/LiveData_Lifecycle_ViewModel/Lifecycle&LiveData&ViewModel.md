# Jetpack

------------------

## Lifecycle    

> Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments. These components help you produce better-organized, and often lighter-weight code, that is easier to maintain.(Lifecycle可以检测Activity或者Fragment的生命周期变化，用来构建更轻量级的代码)
>
> A common pattern is to implement the actions of the dependent components in the lifecycle methods of activities and fragments. However, this pattern leads to a poor organization of the code and to the proliferation of errors. By using lifecycle-aware components, you can move the code of dependent components out of the lifecycle methods and into the components themselves.(当某个组件需要依赖Activity或者Fragment的生命周期的时候，我们通常的做法是在生命周期中实现我们的业务。但是这样会降低代码的组织结构并且引起大量的错误。通过使用Lifecycle组件，我们可以把具体的业务逻辑从Activity或者Fragment的生命周期里转移到各自的组件中)

### 一般用法  
- BigLifecycleActivity.java  
```java
private void observeLifecycle() {
    getLifecycle().addObserver(new LifecycleObserver() {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void observeStart(){
            Log.e("->BigLifecycleActivity", "observeStart@26 --> " + "");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void observeCreate(){
            Log.e("->BigLifecycleActivity", "observeCreate@32 --> " + "");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void observeResume(){
            Log.e("->BigLifecycleActivity", "observeResume@37 --> " + "");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void observePause(){
            Log.e("->BigLifecycleActivity", "observePause@42 --> " + "");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void observeStop(){
            Log.e("->BigLifecycleActivity", "observeStop@47 --> " + "");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void observeDestroy(){
            Log.e("->BigLifecycleActivity", "observeDestroy@52 --> " + "");
        }
    });
}
```
<div align=center><img src="res/Lifecycle_console_0.png"/></div>  
<div align=center><img src="res/Lifecycle_UML.png"/></div>  
