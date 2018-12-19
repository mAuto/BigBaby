package com.mauto.bigbaby.lab.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class BigSampleService extends Service {

    public BigSampleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("--> BigSampleService <--", "onCreate"+"");
    }

    private TimeUnit unit = TimeUnit.SECONDS;
    private int duration = 40;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("--> BigSampleService <--", "onStartCommand"+": "+unit.toString()+" "+duration);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("--> BigSampleService <--", "run");
                startService(new Intent(BigSampleService.this, BigTestService.class));
            }
        }, unit.toMillis(duration));
        return super.onStartCommand(intent, flags, startId);
    }
}
