package com.mauto.bigbaby.lab.popup;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by haohuidong on 19-5-29.
 */

public class SyncActivity extends FragmentActivity {
    @Override
    protected void onDestroy() {
        Log.e(">>> SyncActivity", "onDestroy@13 --> " + "");
        super.onDestroy();
    }
}
