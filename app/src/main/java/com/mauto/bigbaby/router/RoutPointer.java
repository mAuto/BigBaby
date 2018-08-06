package com.mauto.bigbaby.router;

import com.mauto.bigbaby.DiffUtil.BigDiffUtilsActivity;

/**
 * Created by haohuidong on 18-6-11.
 */

public enum RoutPointer {
    DIFFUTILS(BigDiffUtilsActivity.class.getName());

    String mRoutTarget = "";
    RoutPointer(String rout) {
        mRoutTarget = rout;
    }

    public String getTarget(){
        return mRoutTarget;
    }
}
