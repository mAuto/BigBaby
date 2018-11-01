package com.mauto.bigbaby.arch_components.LiveData_Lifecycle_ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mauto.bigbaby.R;

public class BigLifecycleActivity extends AppCompatActivity  {

    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arch_activity_lifecycle);

        getLifecycle().addObserver(mPresenter);

        new MutableLiveData<String>().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

    }
}