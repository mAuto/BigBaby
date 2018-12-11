package com.mauto.bigbaby.jetpack.LiveData_Lifecycle_ViewModel;

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