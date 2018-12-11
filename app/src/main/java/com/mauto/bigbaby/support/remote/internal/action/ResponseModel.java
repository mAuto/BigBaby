package com.mauto.bigbaby.support.remote.internal.action;

/**
 * Created by haohuidong on 18-9-25.
 */

public abstract class ResponseModel<T> {
    public ModelSate state = ModelSate.INIT;
    public String errMsg;
    public T resultBody;
}
