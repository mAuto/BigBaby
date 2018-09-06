package com.mauto.bigbaby.router;

import com.mauto.bigbaby.DiffUtil.BigDiffUtilsActivity;
import com.mauto.bigbaby.err.fragment.BigErrFragmentActivity;
import com.mauto.bigbaby.err.fragment.BigErrMainActivity;
import com.mauto.bigbaby.arch_components.LiveData_Lifecycle.BigLiveDataActivity;
import com.mauto.bigbaby.ut.BigUnitTestActivity;


/**
 * Created by haohuidong on 18-6-11.
 */

public enum RoutPointer {
    ERR_MAIN(BigErrMainActivity.class.getName()),
    ERR_FRAGMENT(BigErrFragmentActivity.class.getName()),
    DIFFUTILS(BigDiffUtilsActivity.class.getName()), LIVEDATA(BigLiveDataActivity.class.getName()),
    UT_MAIN(BigUnitTestActivity.class.getName());


    String mRoutTarget = "";
    RoutPointer(String rout) {
        mRoutTarget = rout;
    }

    public String getTarget(){
        return mRoutTarget;
    }
}
