package com.mauto.bigbaby.support.remote.internal.action;


/**
 * Created by haohuidong on 18-9-25.
 */

public class ErrModel extends ResponseModel {
    public ErrModel(String msg) {
        state = ModelSate.FAILED;
        errMsg = msg;
    }
}
