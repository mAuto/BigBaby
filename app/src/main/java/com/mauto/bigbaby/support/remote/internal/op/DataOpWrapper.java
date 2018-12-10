package com.mauto.bigbaby.support.remote.internal.op;

import com.mauto.bigbaby.support.remote.internal.ErrModel;
import com.mauto.bigbaby.support.remote.internal.LoadingModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DataOpWrapper<T> {

    public executor wrapUp(final DataOp op, final RepositoryAction action) {
        if (op == null || action == null)
            throw new NullPointerException("DataOp or RepositoryAction can not be null.");
        return new executor() {
            @Override
            public void execute() {
                op.fetchData(buildObserver(action));
            }
        };
    }

    private Observer<T> buildObserver(final RepositoryAction action) {
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                action.onAction(new LoadingModel());
            }

            @Override
            public void onNext(T t) {

            }

            @Override
            public void onError(Throwable throwable) {
                action.onAction(new ErrModel(throwable.toString()));
            }

            @Override
            public void onComplete() {

            }
        };
    }

    public interface executor {
        void execute();
    }

}
