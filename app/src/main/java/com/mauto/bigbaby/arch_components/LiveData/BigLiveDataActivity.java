package com.mauto.bigbaby.arch_components.LiveData;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mauto.bigbaby.R;

public class BigLiveDataActivity extends AppCompatActivity {

    private SimulateDataPicker mPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arch_activity_livedata);
        mPicker = new SimulateDataPicker();

        mPicker.fetchRemoteDataSync().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String name) {
                Log.e("--> LiveData <--", "name: "+name);
            }
        });

        getLifecycle().addObserver(new GenericLifecycleObserver(){
            @Override
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {

            }
        });

        new MutableLiveData<String>().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
    }

    public void onClickAction(View view) {
        mPicker.fetchRemoteDataSync();
    }
}
