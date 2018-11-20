package com.mauto.bigbaby.librarys.recyclerview.AsyncListUtil;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.base.BigBaseActivity;
import com.mauto.bigbaby.markdown.MarkdownPointer;

public class BigAsyncListActivity extends BigBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_recycler_asynclist_activity_main);

        updateTitle("AsyncListUtil");
        displayMarkdownEntrance(MarkdownPointer.MD_LINK_LIB_RECYCLER_ASYNCLISTUTIL);

        initViews();
    }

    private RecyclerView mView;
    private BigAsyncListUtil mAsyncUtil;
    private void initViews() {
        mView = findViewById(R.id.rv_items);
        mView.setLayoutManager(new LinearLayoutManager(this));
        mAsyncUtil = new BigAsyncListUtil(mView);
        mView.setAdapter(new BigAsyncAdapter(this, mAsyncUtil));
    }

    public void onClickRefresh(View view) {
        mAsyncUtil.refresh();
    }
}
