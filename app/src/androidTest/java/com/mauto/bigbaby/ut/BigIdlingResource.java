package com.mauto.bigbaby.ut;

import android.support.test.espresso.IdlingResource;

/**
 * Created by haohuidong on 18-9-5.
 */

public class BigIdlingResource implements IdlingResource {
    private ResourceCallback mCallback;
    private BigUnitTestActivity mActivity;

    public BigIdlingResource(BigUnitTestActivity activity){
        mActivity = activity;
    }

    // 唯一标识
    @Override
    public String getName() {
        return BigIdlingResource.class.getName();
    }


    // 底层会通过调用这个方法判断网络请求是否已完成
    @Override
    public boolean isIdleNow() {
        if (mActivity != null && mActivity.isRemoteFinished()){
            if (mCallback != null)
                mCallback.onTransitionToIdle();
            return true;
        }
        return false;
    }

    // 给测试类注册一个回调，传进来的callback会作为isIdleNow中完成时的回调。
    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }
}
