package com.mauto.bigbaby.librarys.Jetpack.LiveData_Lifecycle_ViewModel;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mauto.bigbaby.R;

public class BigLifecycleActivity extends AppCompatActivity  {

    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arch_activity_lifecycle);

        observeLifecycle();

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
}
