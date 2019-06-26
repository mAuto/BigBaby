package com.mauto.bigbaby.librarys.Jetpack.LiveData_Lifecycle_ViewModel;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwnerInitializer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mauto.bigbaby.R;

public class BigLifecycleActivity extends AppCompatActivity  {

    private Presenter mPresenter;
    private IPresenter mOldPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arch_activity_lifecycle);

        observeLifecycleByGeneric();
        observeLifecycle();

        getLifecycle().addObserver(mPresenter);


    }

    private void observeLifecycleByGeneric() {
        getLifecycle().addObserver(new GenericLifecycleObserver() {
            @Override
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                switch (event) {
                    case ON_START:{
                        Log.e("->onStateChanged", "observeStart --> " + "");
                    }break;
                    case ON_CREATE:{
                        Log.e("->onStateChanged", "observeCreate --> " + "");
                    }break;
                    case ON_RESUME:{
                        Log.e("->onStateChanged", "observeResume --> " + "");
                    }break;
                    case ON_PAUSE:{
                        Log.e("->onStateChanged", "observePause --> " + "");
                    }break;
                    case ON_STOP:{
                        Log.e("->onStateChanged", "observeStop --> " + "");
                    }break;
                    case ON_DESTROY:{
                        Log.e("->onStateChanged", "observeDestroy --> " + "");
                    }break;
                    case ON_ANY:{
                        Log.e("->onStateChanged", "observeAny --> " + "");
                    }break;
                }
            }
        });
    }

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

    @Override
    protected void onStart() {
        super.onStart();
        mOldPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOldPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mOldPresenter.onPause();
    }
}
