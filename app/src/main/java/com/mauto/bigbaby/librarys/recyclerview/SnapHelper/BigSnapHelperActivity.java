package com.mauto.bigbaby.librarys.recyclerview.SnapHelper;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.librarys.op.RandomDataOp;
import com.mauto.bigbaby.support.base.BigBaseActivity;
import com.mauto.bigbaby.support.remote.internal.DataRepository;
import com.mauto.bigbaby.support.remote.internal.action.ErrModel;
import com.mauto.bigbaby.support.remote.internal.action.LoadingModel;
import com.mauto.bigbaby.support.remote.internal.action.ResponseModel;
import com.mauto.bigbaby.support.remote.internal.op.RepositoryAction;
import com.mauto.bigbaby.support.remote.model.GankBean;
import com.mauto.bigbaby.support.remote.model.RandomResponseBody;

import java.util.List;

import static com.mauto.bigbaby.support.markdown.MarkdownPointer.MD_LINK_LIB_RECYCLER_SNAP;

public class BigSnapHelperActivity extends BigBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_recycler_snap_helper_main);

        updateTitle("SnapHelper");
        displayMarkdownEntrance(MD_LINK_LIB_RECYCLER_SNAP);

        initHorizontalViews();
        initVerticalViews();
    }

    public void onClickFetchData(View view) {
        DataRepository.fetchDataFromRemote(new RandomDataOp(), new RepositoryAction() {
            @Override
            public void onAction(ResponseModel model) {
                if (model instanceof LoadingModel) {
                    mPbHorizontalLoading.setVisibility(View.VISIBLE);
                    mPbVerticalLoading.setVisibility(View.VISIBLE);
                } else if (model instanceof ErrModel) {
                    mPbHorizontalLoading.setVisibility(View.GONE);
                    mPbVerticalLoading.setVisibility(View.GONE);
                } else {
                    mPbHorizontalLoading.setVisibility(View.GONE);
                    mPbVerticalLoading.setVisibility(View.GONE);

                    List<GankBean> newData = ((RandomResponseBody) model.resultBody).results;
                    mHorizontalAdapter.fillData(newData);
                    mVerticalAdapter.fillData(newData);
                }
            }
        });
    }

    /////////////////////////////////////////--> 18-12-17 下午6:04 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> horizontal list <-- ↓↓↓/////////////////////////////////////
    private RecyclerView mRvHorizontal;
    private RandomSnapAdapter mHorizontalAdapter;
    private ContentLoadingProgressBar mPbHorizontalLoading;

    private void initHorizontalViews() {
        mPbHorizontalLoading = findViewById(R.id.pb_horizontal_loading);
        mRvHorizontal = findViewById(R.id.rv_horizontal);
        mRvHorizontal.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mHorizontalAdapter = new RandomSnapAdapter(this, RecyclerView.HORIZONTAL);
        mRvHorizontal.setAdapter(mHorizontalAdapter);
    }
    /////////////////////////////////////↑↑↑ --> horizontal list <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-17 下午6:04 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> vertical list <-- ↓↓↓/////////////////////////////////////
    private RecyclerView mRvVertical;
    private ContentLoadingProgressBar mPbVerticalLoading;
    private RandomSnapAdapter mVerticalAdapter;

    private void initVerticalViews() {
        mPbVerticalLoading = findViewById(R.id.pb_vertical_loading);
        mRvVertical = findViewById(R.id.rv_vertical);
        mRvVertical.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mVerticalAdapter = new RandomSnapAdapter(this, RecyclerView.VERTICAL);
        mRvVertical.setAdapter(mVerticalAdapter);
    }
    /////////////////////////////////////↑↑↑ --> horizontal list <-- ↑↑↑/////////////////////////////////////
}
