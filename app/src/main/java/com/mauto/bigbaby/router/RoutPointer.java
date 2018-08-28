package com.mauto.bigbaby.router;

import com.mauto.bigbaby.DiffUtil.BigDiffUtilsActivity;
import com.mauto.bigbaby.err.BigErrFragmentActivity;
import com.mauto.bigbaby.err.BigErrMainActivity;

/**
 * Created by haohuidong on 18-6-11.
 */

public enum RoutPointer {
    DIFFUTILS(BigDiffUtilsActivity.class.getName()),
    ERR_MAIN(BigErrMainActivity.class.getName()),
    ERR_FRAGMENT(BigErrFragmentActivity.class.getName());

    String mRoutTarget = "";
    RoutPointer(String rout) {
        mRoutTarget = rout;
    }

    public String getTarget(){
        return mRoutTarget;
    }
}
