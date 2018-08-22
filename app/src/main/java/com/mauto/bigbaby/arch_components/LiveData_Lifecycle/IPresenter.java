package com.mauto.bigbaby.arch_components.LiveData_Lifecycle;

/**
 * Created by haohuidong on 18-8-21.
 */

public interface IPresenter {
    void onStart();
    void onCreate();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
