package com.mauto.bigbaby.target26.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
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

        final long startTime = System.currentTimeMillis();
        final Handler handler = new Handler();

        Runnable action = new Runnable() {
            @Override
            public void run() {
//                try {
                    startService(new Intent(BigServiceActivity.this, BigTestService.class));
                    handler.postDelayed(this, TimeUnit.MINUTES.toMillis(1));
//                } catch (Exception e) {
//                    Log.e("--> BigServiceActivity <--", "cost:"+" "+TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - startTime)));
//                    Log.e("--> BigServiceActivity <--", "err: "+e.toString());
//                }
            }
        };

        handler.postDelayed(action, TimeUnit.MINUTES.toMillis(1));
    }
}
