package com.mauto.bigbaby.lab.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mauto.bigbaby.BuildConfig;
import com.mauto.bigbaby.R;
import com.mauto.bigbaby.tools.Utils;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;
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

//        startService(new Intent(this, BigSampleService.class));

//        sprintPackageName();

        Runnable action = new Runnable() {
            @Override
            public void run() {
                sprintPackageName();
//                try {
                if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
                    startService(new Intent(BigServiceActivity.this, BigTestService.class));
                    Log.e(">>>>>>", "BigServiceActivity --> " + "run"+" start 0");
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        Log.e(">>>>>>", "BigServiceActivity --> " + "run"+" err:"+e.toString());
                    }
                    Log.e(">>>>>>", "BigServiceActivity --> " + "run"+" start 1");
                    handler.postDelayed(this, TimeUnit.MINUTES.toMillis(1));
                }
//                } catch (Exception e) {
//                    Log.e("--> BigServiceActivity <--", "cost:"+" "+TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - startTime)));
//                    Log.e("--> BigServiceActivity <--", "err: "+e.toString());
//                }
            }
        };

        handler.postDelayed(action, TimeUnit.MINUTES.toMillis(1));
    }

    public void onClickStartService(View view) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(">>>>>>", "BigServiceActivity --> " + "run"+" 0");
                ContextCompat.startForegroundService(BigServiceActivity.this, new Intent(BigServiceActivity.this, BigTestService.class));
                try {
                    Thread.sleep(6000);
                    Log.e(">>>>>>", "BigServiceActivity --> " + "run"+" 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, TimeUnit.MINUTES.toMillis(0));
    }

    private void sprintPackageName() {
        String name_0 = getPackageName();
        String name_1 = "big";
        getAppName(this, 0);
        try {
            name_1 = getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(">>>>>>", "BigServiceActivity --> " + "onClickStartServiceDelay"+" err:"+e.toString());
        }

        Log.e(">>>>>>", "BigServiceActivity --> " + "onClickStartServiceDelay"+" name_0:"+name_0);
        Log.e(">>>>>>", "BigServiceActivity --> " + "onClickStartServiceDelay"+" name_1:"+name_1);
    }

    /**
     *
     * @param context 上下文
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(Context context, int pid)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            Log.e(">>>>>>", "BigServiceActivity --> " + "getAppName"+" name:"+info.processName);
            try {
//                if (info.pid == pid) {
//                    return info.processName;
//                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    public void onClickStartServiceTry(View view) {
        Log.e(">>>>>>", "BigServiceActivity --> " + "onClickStartServiceTry"+"");
        final Handler handler = new Handler();

        final Runnable action = new Runnable() {
            @Override
            public void run() {
                Log.e(">>>>>>", "BigServiceActivity --> " + "run"+" api =--> "+Build.VERSION.SDK_INT);
//                ServiceCompat.startService(BigServiceActivity.this, new Intent(BigServiceActivity.this, BigTestService.class), "");
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(new Intent(BigServiceActivity.this, BigTestService.class));
                } else {
                    // Pre-O behavior.
                    startService(new Intent(BigServiceActivity.this, BigTestService.class));
                }
                handler.postDelayed(this, TimeUnit.MINUTES.toMillis(1));
            }
        };

        handler.postDelayed(action, TimeUnit.MINUTES.toMillis(2));
    }

    public void onClickStartLoopThread(View view) {
        new Thread(){
            @SuppressLint("LongLogTag")
            @Override
            public void run() {
                for (;;) {
                    try {
                        sleep(TimeUnit.MINUTES.toMillis(2));
                        Log.e(">>>>>> BigServiceActivity.150", "run --> " + " " + Utils.formatTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
                        Log.e(">>>>>> BigServiceActivity.154", "" + "***");
                    } catch (InterruptedException e) {
                        Log.e(">>>>>> BigServiceActivity.160", "run --> " + " "+e.toString());
                    }
                }
            }
        }.start();
    }
}
