package com.mauto.bigbaby.librarys.recyclerview.AsyncListUtil;

import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;

/**
 * Created by haohuidong on 18-11-16.
 */

public class BigAsyncListUtil extends AsyncListUtil<String> {

    private static int TILE_SIZE = 20;

    public BigAsyncListUtil(RecyclerView recyclerView) {
        super(String.class, TILE_SIZE, new BigDataCallback(), new BigViewCallback(recyclerView));

        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    onRangeChanged();
                }
            });
        }
    }
}
