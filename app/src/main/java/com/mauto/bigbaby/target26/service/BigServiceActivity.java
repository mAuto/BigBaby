package com.mauto.bigbaby.target26.service;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mauto.bigbaby.R;

import java.util.concurrent.TimeUnit;

public class BigServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target26_service_main);
    }

    public void onClickStartServiceDelay(View view) {
//        Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                Log.e("--> BigServiceActivity <--", "handleMessage"+"");
                startService(new Intent(BigServiceActivity.this, BigSampleService.class));
//            }
//        };
//
//        handler.sendEmptyMessageDelayed(0, TimeUnit.SECONDS.toMillis(10));
    }
}
