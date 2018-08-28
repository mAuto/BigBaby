package com.mauto.bigbaby.router;

import com.mauto.bigbaby.DiffUtil.BigDiffUtilsActivity;
<<<<<<< HEAD
import com.mauto.bigbaby.err.BigErrFragmentActivity;
import com.mauto.bigbaby.err.BigErrMainActivity;
=======
import com.mauto.bigbaby.arch_components.LiveData_Lifecycle.BigLiveDataActivity;
>>>>>>> c131c4a4cd5d29fd3a92b8eaad9f38872be72c0b

/**
 * Created by haohuidong on 18-6-11.
 */

public enum RoutPointer {
<<<<<<< HEAD
    DIFFUTILS(BigDiffUtilsActivity.class.getName()),
    ERR_MAIN(BigErrMainActivity.class.getName()),
    ERR_FRAGMENT(BigErrFragmentActivity.class.getName());
=======
    DIFFUTILS(BigDiffUtilsActivity.class.getName()), LIVEDATA(BigLiveDataActivity.class.getName());
>>>>>>> c131c4a4cd5d29fd3a92b8eaad9f38872be72c0b

    String mRoutTarget = "";
    RoutPointer(String rout) {
        mRoutTarget = rout;
    }

    public String getTarget(){
        return mRoutTarget;
    }
}
