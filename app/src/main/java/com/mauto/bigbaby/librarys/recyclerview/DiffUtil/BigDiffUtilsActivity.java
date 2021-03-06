package com.mauto.bigbaby.librarys.recyclerview.DiffUtil;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.ArrayList;

import static com.mauto.bigbaby.support.markdown.MarkdownPointer.MD_LINK_LIB_RECYCLER_DIFFUTIL;

public class BigDiffUtilsActivity extends BigBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diffutils_activity);

        updateTitle("DiffUtil");
        displayMarkdownEntrance(MD_LINK_LIB_RECYCLER_DIFFUTIL);
        
        initViews();
    }

    public void onClickFetchData(View view) {
        fetchData();
    }

    private void fetchData() {
        DataRepository.fetchDataFromRemote(new RandomDataOp(), new RepositoryAction() {
            @Override
            public void onAction(ResponseModel model) {
                if (model instanceof LoadingModel) {
                    mPbLoading.setVisibility(View.VISIBLE);
                } else if (model instanceof ErrModel) {
                    mPbLoading.setVisibility(View.GONE);
                } else {
                    mPbLoading.setVisibility(View.GONE);

                    ArrayList<GankBean> newData = ((RandomResponseBody) model.resultBody).results;
                    ArrayList<GankBean> oldData = (ArrayList<GankBean>) mAdapter.getOriginalData().clone();

                    if (oldData.size() > 2) {
                        for (int i=0;i<2;i++) {
                            GankBean bean = newData.get(i*2);
                            bean._id = oldData.get(i)._id;
                            bean.desc = "test desc: "+(i*2);
//                            newData.remove(i*2);
//                            newData.add (i*2, oldData.get(i));
                        }
                    }

                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(oldData, newData), true);

                    mAdapter.fillData(newData);

                    diffResult.dispatchUpdatesTo(mAdapter);
                }
            }
        });
    }

    /////////////////////////////////////////--> 18-7-13 下午3:00 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> init views <-- ↓↓↓/////////////////////////////////////
    private RecyclerView mRvSampleList;
    private RandomAdapter mAdapter;
    private ContentLoadingProgressBar mPbLoading;

    private void initViews(){
        mRvSampleList = findViewById(R.id.rv_remote_data);
        mRvSampleList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RandomAdapter(this);
        mRvSampleList.setAdapter(mAdapter);

        mPbLoading = findViewById(R.id.pb_loading);
    }
    /////////////////////////////////////↑↑↑ --> init views <-- ↑↑↑/////////////////////////////////////
}
