package com.mauto.bigbaby.lab.popup;

import android.support.v4.app.DialogFragment;
import android.util.Log;

/**
 * Created by haohuidong on 19-5-29.
 */

public class SyncDialogFragment extends DialogFragment {
    @Override
    public void dismiss() {
        Log.e(">>> SyncDialogFragment", "dismiss@13 --> " + "");
        super.dismiss();
    }
}
