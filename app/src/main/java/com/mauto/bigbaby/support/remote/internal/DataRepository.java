package com.mauto.bigbaby.support.remote.internal;

import android.util.Log;

import com.mauto.bigbaby.support.remote.internal.NetHunter;
import com.mauto.bigbaby.support.remote.internal.op.DataOp;
import com.mauto.bigbaby.support.remote.internal.op.DataOpWrapper;
import com.mauto.bigbaby.support.remote.internal.op.RepositoryAction;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataRepository {

    public static <T> void fetchDataFromRemote(DataOp<T> op, RepositoryAction action) {
        new DataOpWrapper<T>().wrapUp(op, action).execute();
    }

}
