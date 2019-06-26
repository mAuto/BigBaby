package com.mauto.bigbaby.librarys.Jetpack.LiveData_Lifecycle_ViewModel;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

/**
 * Created by haohuidong on 18-8-21.
 */

public class Presenter implements GenericLifecycleObserver, IPresenter {
    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        switch (event){
            case ON_START:{
                // do something when START
            }break;
            case ON_CREATE:{
                // do something when CREATE
            }break;
            case ON_RESUME:{
                // do something when RESUME
            }break;
            case ON_PAUSE:{
                // do something when PAUSE
            }break;
            case ON_STOP:{
                // do something when STOP
            }break;
            case ON_DESTROY:{
                // do something when DESTROY
            }break;
        }
    }
}
