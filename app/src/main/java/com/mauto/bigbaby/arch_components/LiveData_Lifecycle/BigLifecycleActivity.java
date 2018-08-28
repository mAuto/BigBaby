package com.mauto.bigbaby.arch_components.LiveData_Lifecycle;

<<<<<<< HEAD:app/src/main/java/com/mauto/bigbaby/arch_components/LiveData/BigLiveDataActivity.java
=======
import android.app.Activity;
import android.arch.lifecycle.GenericLifecycleObserver;
>>>>>>> c131c4a4cd5d29fd3a92b8eaad9f38872be72c0b:app/src/main/java/com/mauto/bigbaby/arch_components/LiveData_Lifecycle/BigLifecycleActivity.java
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mauto.bigbaby.R;

public class BigLifecycleActivity extends AppCompatActivity  {

    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arch_activity_lifecycle);

<<<<<<< HEAD:app/src/main/java/com/mauto/bigbaby/arch_components/LiveData/BigLiveDataActivity.java
=======
        getLifecycle().addObserver(mPresenter);

>>>>>>> c131c4a4cd5d29fd3a92b8eaad9f38872be72c0b:app/src/main/java/com/mauto/bigbaby/arch_components/LiveData_Lifecycle/BigLifecycleActivity.java
        new MutableLiveData<String>().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

    }
}
