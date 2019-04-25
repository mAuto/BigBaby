package com.mauto.bigbaby;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.mauto.bigbaby.tools.GlobalAttributes;

/**
 * Created by haohuidong on 18-10-31.
 */

public class BabyApplication extends Application {

    private static BabyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("--> Application <--", "created");
        mApplication = this;

        GlobalAttributes.initGlobalAttribute();
    }

    public static BabyApplication getApplication() {
        return mApplication;
    }
}
