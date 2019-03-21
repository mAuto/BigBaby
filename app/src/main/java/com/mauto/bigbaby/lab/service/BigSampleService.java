package com.mauto.bigbaby.lab.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.lab.news.Constants;

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
        startForeground();
        Log.e("--> BigSampleService <--", "onCreate"+"");
    }

    private TimeUnit unit = TimeUnit.SECONDS;
    private int duration = 40;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("--> BigSampleService <--", "onStartCommand"+": "+unit.toString()+" "+duration);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("--> BigSampleService <--", "run");
//                startService(new Intent(BigSampleService.this, BigTestService.class));
//            }
//        }, unit.toMillis(duration));
        return super.onStartCommand(intent, flags, startId);
    }

    /////////////////////////////////////////--> 18-11-5 下午4:05 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> for target 26 <-- ↓↓↓/////////////////////////////////////
    private void startForeground() {
        if (Build.VERSION.SDK_INT >= 26) {
            Log.e("--> BigTestService <--", "startForeground"+"");
            startForeground(Constants.TEST_NOTIFICATION_ID,
                    new NotificationCompat.Builder(this, "screen_service")
                            .setSmallIcon(R.mipmap.ic_big_launcher)
                            .setOngoing(true).setPriority(Notification.PRIORITY_MIN)
                            .setCategory(NotificationCompat.CATEGORY_SERVICE).build());
        }
    }
    /////////////////////////////////////↑↑↑ --> for target 26 <-- ↑↑↑/////////////////////////////////////
}
