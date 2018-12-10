package com.mauto.bigbaby.librarys.recyclerview.DiffUtil;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.support.base.BigBaseActivity;
import com.mauto.bigbaby.support.remote.internal.NetHunter;
import com.mauto.bigbaby.support.remote.internal.ResponseBody;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.mauto.bigbaby.support.markdown.MarkdownPointer.MD_LINK_LIB_RECYCLER_DIFFUTIL;

public class BigDiffUtilsActivity extends BigBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diffutils_activity);

        updateTitle("DiffUtil");
        displayMarkdownEntrance(MD_LINK_LIB_RECYCLER_DIFFUTIL);
        
        initViews();

        testFetchData();
    }

    public void onClickFetchData(View view) {

    }

    private void testFetchData() {
        Log.e("--> BigDiffUtilsActivity <--", "testFetchData"+"");
        NetHunter.getInstance().getApiPointer().fetchAndroidRandomData(20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("--> BigDiffUtilsActivity <--", "onSubscribe"+"");
                    }

                    @Override
                    public void onNext(ResponseBody responseModel) {
                        Log.e("--> BigDiffUtilsActivity <--", "onNext"+" result:"+responseModel.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("--> BigDiffUtilsActivity <--", "onError"+" err:"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("--> BigDiffUtilsActivity <--", "onComplete"+"");
                    }
                });
    }

    /////////////////////////////////////////--> 18-7-13 下午3:00 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> init views <-- ↓↓↓/////////////////////////////////////
    private RecyclerView mRvSampleList;

    private void initViews(){
        mRvSampleList = findViewById(R.id.rv_remote_data);
        mRvSampleList.setLayoutManager(new LinearLayoutManager(this));
    }
    /////////////////////////////////////↑↑↑ --> init views <-- ↑↑↑/////////////////////////////////////
}
