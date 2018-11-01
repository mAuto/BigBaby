package com.mauto.bigbaby;

import android.app.Application;
import android.util.Log;

/**
 * Created by haohuidong on 18-10-31.
 */

public class BabyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("--> Application <--", "created");
    }
}
