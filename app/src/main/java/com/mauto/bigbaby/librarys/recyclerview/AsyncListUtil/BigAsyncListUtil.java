package com.mauto.bigbaby.librarys.recyclerview.AsyncListUtil;

import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;

/**
 * Created by haohuidong on 18-11-16.
 */

public class BigAsyncListUtil extends AsyncListUtil<String> {

    // 分页大小，每页20条数据
    private static int TILE_SIZE = 20;

    public BigAsyncListUtil(RecyclerView recyclerView) {
        super(String.class, TILE_SIZE, new BigDataCallback(), new BigViewCallback(recyclerView));

        if (recyclerView != null) {
            // 监听RecyclerView的滑动事件，即使对其视界范围做出响应
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    // 更新当前可见范围
                    onRangeChanged();
                }
            });
        }
    }
}
