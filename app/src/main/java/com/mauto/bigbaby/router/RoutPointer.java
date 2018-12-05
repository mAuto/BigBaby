package com.mauto.bigbaby.router;

import com.mauto.bigbaby.librarys.recyclerview.DiffUtil.BigDiffUtilsActivity;
import com.mauto.bigbaby.err.BigErrMainActivity;
import com.mauto.bigbaby.err.fragment.tag.BigErrTagActivity;
import com.mauto.bigbaby.err.fragment.attach.BigErrAttachActivity;
import com.mauto.bigbaby.err.fragment.BigErrFragmentActivity;
import com.mauto.bigbaby.jetpack.LiveData_Lifecycle_ViewModel.BigLiveDataActivity;
import com.mauto.bigbaby.librarys.BigLibrariesActivity;
import com.mauto.bigbaby.librarys.recyclerview.AsyncListUtil.BigAsyncListActivity;
import com.mauto.bigbaby.librarys.recyclerview.BigLibRecyclerActivity;
import com.mauto.bigbaby.librarys.recyclerview.SortedList.BigSortedActivity;
import com.mauto.bigbaby.thread.BigThreadActivity;
import com.mauto.bigbaby.ut.BigUnitTestActivity;


/**
 * Created by haohuidong on 18-6-11.
 */

public enum RoutPointer {
    // err
    ERR_MAIN(BigErrMainActivity.class.getName()),
    ERR_FRAGMENT(BigErrFragmentActivity.class.getName()),
    ERR_TAG(BigErrTagActivity.class.getName()),
    ERR_ATTACH(BigErrAttachActivity.class.getName()),

    DIFFUTILS(BigDiffUtilsActivity.class.getName()), LIVEDATA(BigLiveDataActivity.class.getName()),
    UT_MAIN(BigUnitTestActivity.class.getName()),

    THREAD(BigThreadActivity.class.getName()),


    /////////////////////////////////////////--> 18-11-2 上午11:19 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> lib <-- ↓↓↓/////////////////////////////////////
    LIB_MAIN(BigLibrariesActivity.class.getName()),
    LIB_RECYCLER_MAIN(BigLibRecyclerActivity.class.getName()),
    LIB_RECYCLER_SORTED(BigSortedActivity.class.getName()),
    LIB_RECYCLER_ASYNC(BigAsyncListActivity.class.getName());
    /////////////////////////////////////↑↑↑ --> lib <-- ↑↑↑/////////////////////////////////////

    String mRoutTarget = "";
    RoutPointer(String rout) {
        mRoutTarget = rout;
    }

    public String getTarget(){
        return mRoutTarget;
    }
}
