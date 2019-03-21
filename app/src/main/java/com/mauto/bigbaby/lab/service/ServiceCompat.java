package com.mauto.bigbaby.lab.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by haohuidong on 18-12-21.
 */

public class ServiceCompat {

    // 我需要反馈数据，我知道这样不对，但我觉得应该有更好的办法解决问题，而不是滥用startForegroundService。
    public static void startService(Context context, Intent intent, String tracker) {
        if (context == null || intent == null)
            return;

        getHiddenTarget();

        try {
            Log.e(">>>>>>", "ServiceCompat --> " + "startService"+" 0");
            context.startService(intent);
            Log.e(">>>>>>", "ServiceCompat --> " + "startService"+" 1");
        } catch (IllegalStateException e) {
            Log.e(">>>>>>", "ServiceCompat --> " + "startService"+" err:"+e.toString());
            Log.e(">>>>>>", "ServiceCompat --> " + "startService"+" 3");
            startForegroundService(context, intent);
            Log.e(">>>>>>", "ServiceCompat --> " + "startService"+" 4");
        }
    }

    public static void startForegroundService(Context context, Intent intent) {
        if (context == null || intent == null)
            return;
        ContextCompat.startForegroundService(context, intent);
        Log.e(">>>>>>", "ServiceCompat --> " + "startForegroundService"+"");
    }

    private static void getHiddenTarget() {
        try {
            Class serviceManager = Class.forName("android.app.ActivityThread");
            Method method = serviceManager.getMethod("currentProcessName");
            String name = (String) method.invoke(null);
            Log.e(">>>>>>", "ServiceCompat --> " + "getHiddenTarget"+" name:"+name);
        } catch (Exception e) {
            Log.e(">>>>>>", "ServiceCompat --> " + "getHiddenTarget"+" err:"+e.toString());
        }
    }

}
