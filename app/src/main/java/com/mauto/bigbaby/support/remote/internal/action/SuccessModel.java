package com.mauto.bigbaby.support.remote.internal.action;

/**
 * Created by haohuidong on 18-12-11.
 */

public class SuccessModel<T> extends ResponseModel {
    public SuccessModel(T results){
        this.resultBody = results;
    }
}
