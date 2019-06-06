package com.mauto.bigbaby.lab.popup;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by haohuidong on 19-5-29.
 */

public class SyncDialog extends AlertDialog implements Comparable<SyncEntry>, SyncEntry {

    public SyncDialog(@NonNull Context context) {
        super(context);
    }

    public SyncDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SyncDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void dismiss() {
        Log.e(">>> SyncDialog", "dismiss@28 --> " + "");
        super.dismiss();
    }

    @Override
    public int compareTo(@NonNull SyncEntry o) {
        return mOptions.mPriority - o.mOptions.mPriority;
    }

    @Override
    public void setPriority(int priority) {
        mOptions.mPriority = priority;
    }

    @Override
    public void setEntryId(String entryId) {
        mOptions.mEntryId = entryId;
    }

    @Override
    public void setNextEntry(SyncEntryOptions nextEntry) {
        mOptions.mNext = nextEntry;
    }

    @Override
    public String checkNextEntry() {
        return mOptions.mNext.mEntryId;
    }

    public static class Builder extends AlertDialog.Builder{

        private int mPriority;
        private String mEntryId = "";
        private SyncEntryOptions mNextEntry = null;

        public Builder(Context context) {
            super(context);
        }

        public Builder(Context context, int themeResId) {
            super(context, themeResId);
        }

        public Builder setPriority(int priority) {
            mPriority = priority;
            return this;
        }

        public Builder setEntryKey(String entryId) {
            mEntryId = entryId;
            return this;
        }

        public Builder setNextEntry(SyncEntryOptions nextEntry) {
            mNextEntry = nextEntry;
            return this;
        }

        @Override
        public SyncDialog create() {
            SyncDialog dialog = (SyncDialog) super.create();
            dialog.setPriority(mPriority);
            dialog.setEntryId(mEntryId);
            dialog.setNextEntry(mNextEntry);
            return dialog;
        }
    }

}
