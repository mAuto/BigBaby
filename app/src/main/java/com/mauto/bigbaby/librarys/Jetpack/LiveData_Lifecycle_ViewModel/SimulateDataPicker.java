package com.mauto.bigbaby.librarys.Jetpack.LiveData_Lifecycle_ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

/**
 * Created by haohuidong on 18-8-17.
 */

public class SimulateDataPicker {
    private MutableLiveData<String> mLiveData;

    public SimulateDataPicker() {
        mLiveData = new MutableLiveData<>();
    }

    public MutableLiveData getLiveData(){
        return mLiveData;
    }

    public MutableLiveData fetchRemoteData(){
        return mLiveData;
    }

    String nameTmp;
    public MutableLiveData fetchRemoteDataSync(){
        for (String name : DataBuilder.names){
            final String nameTmp = name;
            new Thread(){
                @Override
                public void run() {
                    Log.e("--> LiveData <--", "nameTmp: "+nameTmp);
                    mLiveData.postValue(nameTmp);
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {

                    }
                }
            }.start();
        }
        return mLiveData;
    }

}
