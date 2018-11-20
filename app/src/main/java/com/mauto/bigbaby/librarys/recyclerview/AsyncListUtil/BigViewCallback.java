package com.mauto.bigbaby.librarys.recyclerview.AsyncListUtil;

import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by haohuidong on 18-11-16.
 */

public class BigViewCallback extends AsyncListUtil.ViewCallback {

    private RecyclerView mView;

    public BigViewCallback(RecyclerView view) {
        mView = view;
    }

    // 确定当前视界范围
    @Override
    public void getItemRangeInto(int[] outRange) {
//        Log.e("--> BigViewCallback <--", "getItemRangeInto"+" out_range_size:"+outRange.length);
        LinearLayoutManager manager = (LinearLayoutManager) mView.getLayoutManager();
        outRange[0] = manager.findFirstVisibleItemPosition();
        outRange[1] = manager.findLastVisibleItemPosition();
    }

    // 刷新整个列表
    @Override
    public void onDataRefresh() {
        Log.e("--> BigViewCallback <--", "onDataRefresh"+"");
        mView.getAdapter().notifyDataSetChanged();
    }

    // 刷新单个item
    @Override
    public void onItemLoaded(int position) {
        Log.e("--> BigViewCallback <--", "onItemLoaded"+" position:"+position);
        mView.getAdapter().notifyItemChanged(position);
    }
}
