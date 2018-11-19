package com.mauto.bigbaby.librarys.recyclerview.AsyncList;

import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by haohuidong on 18-11-16.
 */

public class BigViewCallback extends AsyncListUtil.ViewCallback {

    private RecyclerView mView;

    public BigViewCallback(RecyclerView view) {
        mView = view;
    }

    @Override
    public void getItemRangeInto(int[] outRange) {
        LinearLayoutManager manager = (LinearLayoutManager) mView.getLayoutManager();
        outRange[0] = manager.findFirstVisibleItemPosition();
        outRange[1] = manager.findLastVisibleItemPosition();
    }

    @Override
    public void onDataRefresh() {
        mView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemLoaded(int position) {
        mView.getAdapter().notifyItemChanged(position);
    }
}
