package com.mauto.bigbaby.tools;

import android.content.Context;
import android.view.WindowManager;
import com.mauto.bigbaby.BabyApplication;

/**
 * Created by haohuidong on 19-3-8.
 * 用来存储在整个app生命周期内都会用到的一些基本属性。
 * 这些属性的获取只在app初始化的时候，一旦被赋值就不应该改变，
 * 所以均不具备setter方，只有getter方法。
 */

public class GlobalAttributes {

    //region singleton
    /*
    * 19-3-8 下午5:00
    * */
    /////////////////////////////////////↓↓↓ --> init singleton <-- ↓↓↓/////////////////////////////////////
    private GlobalAttributes() {
        initScreenOptions();
    }

    private static class InstanceHolder {
        private static GlobalAttributes mInstance = new GlobalAttributes();
    }

    public static GlobalAttributes getAttribute() {
        return InstanceHolder.mInstance;
    }

    public static void initGlobalAttribute() {
        GlobalAttributes.getAttribute();
    }
    /////////////////////////////////////↑↑↑ --> init singleton <-- ↑↑↑/////////////////////////////////////
    //endregion

    //region width and height of screen
    /*
    * 19-3-8 下午5:25
    * */
    /////////////////////////////////////↓↓↓ --> screen options <-- ↓↓↓/////////////////////////////////////
    private int mScreenWidth = -1, mScreenHeight = -1;

    private void initScreenOptions() {
        WindowManager wm = (WindowManager) BabyApplication.getApplication()
                .getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeight = wm.getDefaultDisplay().getHeight();
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }
    /////////////////////////////////////↑↑↑ --> screen options <-- ↑↑↑/////////////////////////////////////
    //endregion
}
