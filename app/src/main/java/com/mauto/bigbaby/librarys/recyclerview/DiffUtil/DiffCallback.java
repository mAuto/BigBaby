package com.mauto.bigbaby.librarys.recyclerview.DiffUtil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        Bundle payload = null;
        GankBean oldBean = mOldData.get(oldItemPosition);
        GankBean newBean = mNewData.get(newItemPosition);

        if (!TextUtils.equals(oldBean.desc, newBean.desc)) {
            if (payload == null)
                payload  = new Bundle();
            payload.putString("desc", newBean.desc);
        }
        if (!TextUtils.equals(oldBean.type, newBean.type)) {
            if (payload == null)
                payload  = new Bundle();
            payload.putString("type", newBean.type);
        }

        if (!TextUtils.equals(oldBean.createAt, newBean.createAt)
                || !TextUtils.equals(oldBean.publishedAt, newBean.publishedAt)
                || !TextUtils.equals(oldBean.source, newBean.source)
                || !TextUtils.equals(oldBean.url, newBean.url)
                || !TextUtils.equals(oldBean.who, newBean.who)
                || !TextUtils.equals(oldBean.used, newBean.used))
            if (payload == null)
                payload  = new Bundle();

        return payload;
    }
}
