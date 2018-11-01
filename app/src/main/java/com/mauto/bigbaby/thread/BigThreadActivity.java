package com.mauto.bigbaby.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.tools.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class BigThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_main_activity);
    }

    public void onClickCompute(View view) {
        Log.e("--> thread <--", "compute start");
        final long startMill = System.currentTimeMillis();
        new Thread(){
            @Override
            public void run() {
                long num = 0;
                while (num < 50000000000l) {
                    num += 1;
                }

                long endMill = System.currentTimeMillis();
                Log.e("--> thread <--", "compute cost: "+ Utils.formatTime(endMill - startMill, "mm:ss") + " num:"+num);
            }
        }.start();
    }

    AtomicLong num = new AtomicLong(0);
    public void onClickComputeSyn(View view) {
        Log.e("--> thread <--", "compute start");
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));

        final Object lock = new Object();

        final long startMill = System.currentTimeMillis();
        for (int i=0;i<5;i++) {
            Runnable computeTask = new Runnable() {
                @Override
                public void run() {
                    while (num.get() < 50000000000L) {
                        num.set(num.get()+1);

                    }
                    long endMill = System.currentTimeMillis();
                    Log.e("--> thread <--", "pool compute cost: "+ Utils.formatTime(endMill - startMill, "mm:ss") + " num:"+num);
                }
            };
            pool.execute(computeTask);
        }

    }
}