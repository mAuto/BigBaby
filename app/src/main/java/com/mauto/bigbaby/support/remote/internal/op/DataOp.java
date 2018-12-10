package com.mauto.bigbaby.support.remote.internal.op;

import io.reactivex.Observer;

public interface DataOp<T> {
    void fetchData(Observer<T> observer);
}
