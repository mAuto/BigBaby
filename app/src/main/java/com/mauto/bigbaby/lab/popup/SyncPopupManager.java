package com.mauto.bigbaby.lab.popup;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by haohuidong on 19-5-30.
 */

public class SyncPopupManager {

    //region >>> singleton
    /*
    * 19-5-30 下午4:57
    * */
    /////////////////////////////////////↓↓↓↓↓↓↓↓↓/////////////////////////////////////
    private SyncPopupManager(){
        mObservable = new Observable();
    }

    private static class InstanceHolder{
        private static SyncPopupManager mInstance = new SyncPopupManager();
    }

    public static SyncPopupManager getInstance() {
        return InstanceHolder.mInstance;
    }
    /////////////////////////////////////↑↑↑↑↑↑↑↑↑/////////////////////////////////////

    //region >>> 以观察者模式为基础的监听模式
    /*
    * 19-5-30 下午4:59
    * */
    /////////////////////////////////////↓↓↓↓↓↓↓↓↓/////////////////////////////////////
    private Observable mObservable = null;

    public void addSyncPopupTransaction(Observer observer) {
        mObservable.addObserver(observer);
        mObservable.notifyObservers();
    }
    /////////////////////////////////////↑↑↑↑↑↑↑↑↑/////////////////////////////////////
}
