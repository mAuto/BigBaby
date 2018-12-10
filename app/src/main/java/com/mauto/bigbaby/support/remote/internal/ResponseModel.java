package com.mauto.bigbaby.support.remote.internal;

/**
 * Created by haohuidong on 18-9-25.
 */

public abstract class ResponseModel {
    public ModelSate state = ModelSate.INIT;
    public String errMsg;
    public String results;
}
