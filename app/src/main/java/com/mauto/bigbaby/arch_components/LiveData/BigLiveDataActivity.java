package com.mauto.bigbaby.arch_components.LiveData;

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
    }

    public void onClickAction(View view) {
        mPicker.fetchRemoteDataSync();
    }
}
