package com.mauto.bigbaby.librarys.op;

import com.mauto.bigbaby.support.remote.internal.NetHunter;
import com.mauto.bigbaby.support.remote.internal.op.DataOp;
import com.mauto.bigbaby.support.remote.model.RandomResponseBody;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by haohuidong on 18-12-11.
 */

public class RandomDataOp implements DataOp<RandomResponseBody> {
    @Override
    public void fetchData(Observer<RandomResponseBody> observer) {
        NetHunter.getInstance().getApiPointer().fetchAndroidRandomData(20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
