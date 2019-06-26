package com.mauto.bigbaby.support.router;

import android.util.Log;

import com.mauto.bigbaby.lab.Toast.BigLabToastActivity;
import com.mauto.bigbaby.lab.anim.BigAnimActivity;
import com.mauto.bigbaby.lab.concurrent.BigLabConcurrentActivity;
import com.mauto.bigbaby.lab.dialog.BigLabDialogActivity;
import com.mauto.bigbaby.lab.permission.BigLabPerActivity;
import com.mauto.bigbaby.lab.popup.BigLabPopupActivity;
import com.mauto.bigbaby.lab.sms.BigLabSmsActivity;
import com.mauto.bigbaby.lab.store.BigLabStoreActivity;
import com.mauto.bigbaby.lab.terminal.BigLabTerminalActivity;
import com.mauto.bigbaby.librarys.Jetpack.BigJetPackActivity;
import com.mauto.bigbaby.librarys.Jetpack.LiveData_Lifecycle_ViewModel.BigLifecycleActivity;
import com.mauto.bigbaby.librarys.recyclerview.AsyncListDiffer.BigListDifferActivity;
import com.mauto.bigbaby.librarys.recyclerview.DiffUtil.BigDiffUtilsActivity;
import com.mauto.bigbaby.err.BigErrMainActivity;
import com.mauto.bigbaby.err.fragment.tag.BigErrTagActivity;
import com.mauto.bigbaby.err.fragment.attach.BigErrAttachActivity;
import com.mauto.bigbaby.err.fragment.BigErrFragmentActivity;
import com.mauto.bigbaby.librarys.Jetpack.LiveData_Lifecycle_ViewModel.BigLiveDataActivity;
import com.mauto.bigbaby.librarys.BigLibrariesActivity;
import com.mauto.bigbaby.librarys.recyclerview.AsyncListUtil.BigAsyncListActivity;
import com.mauto.bigbaby.librarys.recyclerview.BigLibRecyclerActivity;
import com.mauto.bigbaby.librarys.recyclerview.SnapHelper.BigSnapHelperActivity;
import com.mauto.bigbaby.librarys.recyclerview.SortedList.BigSortedActivity;
import com.mauto.bigbaby.lab.service.BigServiceActivity;
import com.mauto.bigbaby.lab.thread.BigThreadActivity;
import com.mauto.bigbaby.ut.BigUnitTestActivity;

import static com.mauto.bigbaby.support.markdown.MarkdownPointer.MD_LINK_ALG_ELSE;
import static com.mauto.bigbaby.support.markdown.MarkdownPointer.MD_LINK_ALG_LINEAR_LIST;
import static com.mauto.bigbaby.support.markdown.MarkdownPointer.MD_LINK_NOTE;


/**
 * Created by haohuidong on 18-6-11.
 */

public enum RoutPointer {
    // err
    ERR_MAIN(BigErrMainActivity.class.getName()),
    ERR_FRAGMENT(BigErrFragmentActivity.class.getName()),
    ERR_TAG(BigErrTagActivity.class.getName()),
    ERR_ATTACH(BigErrAttachActivity.class.getName()),
    LIVEDATA(BigLiveDataActivity.class.getName()),
    UT_MAIN(BigUnitTestActivity.class.getName()),

    THREAD(BigThreadActivity.class.getName()),

    /////////////////////////////////////////--> 18-11-2 上午11:19 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> lib <-- ↓↓↓/////////////////////////////////////
    LIB_MAIN(BigLibrariesActivity.class.getName()),
    LIB_RECYCLER_MAIN(BigLibRecyclerActivity.class.getName()),
    LIB_RECYCLER_SORTED(BigSortedActivity.class.getName()),
    LIB_RECYCLER_ASYNC(BigAsyncListActivity.class.getName()),
    LIB_RECYCLER_DIFF(BigDiffUtilsActivity.class.getName()),
    LIB_RECYCLER_DIFFER(BigListDifferActivity.class.getName()),
    LIB_RECYCLER_SNAP(BigSnapHelperActivity.class.getName()),

    LIB_JETPACK_MAIN(BigJetPackActivity.class.getName()),
    LIB_JETPACK_LIFECYCLE(BigLifecycleActivity.class.getName()),
    /////////////////////////////////////↑↑↑ --> lib <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-14 上午11:02 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> target26 service <-- ↓↓↓/////////////////////////////////////
    TARGET26_SERVICE(BigServiceActivity.class.getName()),
    /////////////////////////////////////↑↑↑ --> target26 service <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-18 下午6:58 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Algorithm <-- ↓↓↓/////////////////////////////////////
    ALGORITHM_ELSE(MD_LINK_ALG_ELSE),
    ALGORITHM_LINEAR_LIST(MD_LINK_ALG_LINEAR_LIST),
    /////////////////////////////////////↑↑↑ --> Algorithm <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-18 下午7:15 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> note <-- ↓↓↓/////////////////////////////////////
    NOTE_MAIN(MD_LINK_NOTE),
    /////////////////////////////////////↑↑↑ --> note <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-19 上午10:45 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> lab <-- ↓↓↓/////////////////////////////////////
    LAB_PERMISSION(BigLabPerActivity.class.getName()),
    LAB_TOAST(BigLabToastActivity.class.getName()),
    LAB_ANIM(BigAnimActivity.class.getName()),
    LAB_SMS(BigLabSmsActivity.class.getName()),
    LAB_TERMINAL(BigLabTerminalActivity.class.getName()),
    LAB_CONCURRENT(BigLabConcurrentActivity.class.getName()),
    LAB_DIALOG_QUEUE(BigLabDialogActivity.class.getName()),
    LAB_STORE(BigLabStoreActivity.class.getName()),
    LAB_POPUP(BigLabPopupActivity.class.getName()),
    /////////////////////////////////////↑↑↑ --> lab <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-14 下午5:22 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Just for occupancy <-- ↓↓↓/////////////////////////////////////
    // 猜猜为什么放一个占位枚举？
    @Deprecated
    OCCUPANCY(Log.class.getName());
    /////////////////////////////////////↑↑↑ --> Just for occupancy <-- ↑↑↑/////////////////////////////////////
    
    String mRoutTarget = "";
    RoutPointer(String rout) {
        mRoutTarget = rout;
    }

    public String getTarget(){
        return mRoutTarget;
    }
}
