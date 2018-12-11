package com.mauto.bigbaby.librarys.recyclerview.DiffUtil;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.mauto.bigbaby.support.remote.model.GankBean;
import com.mauto.bigbaby.support.remote.model.RandomResponseBody;

import java.util.List;

/**
 * Created by haohuidong on 18-12-11.
 */

public class DiffCallback extends DiffUtil.Callback {

    private List<GankBean> mOldData, mNewData;

    public DiffCallback(@NonNull List<GankBean> oldData, @NonNull List<GankBean> newData) {
        mNewData = newData;
        mOldData = oldData;
    }

    @Override
    public int getOldListSize() {
        return mOldData == null ? 0 : mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData == null ? 0 : mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return TextUtils.equals(mOldData.get(i)._id, mNewData.get(i1)._id);
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return mOldData.get(i).isEqualsTo(mNewData.get(i1));
    }
}
