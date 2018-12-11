package com.mauto.bigbaby.support.remote.internal;


import com.mauto.bigbaby.support.remote.internal.op.DataOp;
import com.mauto.bigbaby.support.remote.internal.op.DataOpWrapper;
import com.mauto.bigbaby.support.remote.internal.op.RepositoryAction;

public class DataRepository {

    public static <T> void fetchDataFromRemote(DataOp<T> op, RepositoryAction action) {
        new DataOpWrapper<T>().wrapUp(op, action).execute();
    }

}
