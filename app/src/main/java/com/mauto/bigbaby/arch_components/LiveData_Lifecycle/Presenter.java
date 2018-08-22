package com.mauto.bigbaby.arch_components.LiveData_Lifecycle;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

/**
 * Created by haohuidong on 18-8-21.
 */

public class Presenter implements GenericLifecycleObserver {
    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        switch (event){
            case ON_START:{
                // do something
            }break;
            case ON_CREATE:{
                // do something
            }break;
            case ON_RESUME:{
                // do something
            }break;
            case ON_PAUSE:{
                // do something
            }break;
            case ON_STOP:{
                // do something
            }break;
            case ON_DESTROY:{
                // do something
            }break;
        }
    }
}
