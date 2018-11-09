package com.mauto.bigbaby.target26.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BigTestService extends Service {
    public BigTestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("--> BigTestService <--", "onCreate"+"");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("--> BigTestService <--", "onStartCommand"+"");
        return super.onStartCommand(intent, flags, startId);
    }
}
