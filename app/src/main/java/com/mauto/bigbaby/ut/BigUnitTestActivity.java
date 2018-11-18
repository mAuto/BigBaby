package com.mauto.bigbaby.ut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mauto.bigbaby.R;

public class BigUnitTestActivity extends AppCompatActivity {

    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ut_main_activity);

        mTvContent = findViewById(R.id.tv_content);
    }

    public void onClickFillData(View view) {
        fillList();
    }

    /////////////////////////////////////////--> 18-9-4 上午11:49 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> build data <-- ↓↓↓/////////////////////////////////////
    /**
     * only build once
     * */
    String[] mSampleData;

    private void buildData() {
        if (mSampleData == null){
            mSampleData = new String[]{
                    "Room For 2",
                    "Be The one",
                    "No Lie",
                    "Homesick",
                    "Blow Your Mind",
                    "IDGAF",
                    "One Kiss",
                    "New Rules",
                    "FRIENDS"
            };
        }
    }
    /////////////////////////////////////↑↑↑ --> build data <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-9-4 下午5:02 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> fill list <-- ↓↓↓/////////////////////////////////////
    private void fillList() {
        buildData();

        // ListView
        ListView lvSample = findViewById(R.id.lv_sample);
        BigLvAdapter lvAdapter = new BigLvAdapter(this);
        lvSample.setAdapter(lvAdapter);
        lvAdapter.setData(mSampleData);
        lvSample.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTvContent.setText("LV -> " + mSampleData[position]);
            }
        });

        // RecyclerView
        RecyclerView rvSample = findViewById(R.id.rv_sample);
        rvSample.setLayoutManager(new LinearLayoutManager(this));
        BigRvAdapter rvAdapter = new BigRvAdapter(this);
        rvSample.setAdapter(rvAdapter);
        rvAdapter.setData(mSampleData);
        rvAdapter.setOnItemClickListener(new BigRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                mTvContent.setText("RV -> " + mSampleData[pos]);
            }
        });
    }
    /////////////////////////////////////↑↑↑ --> fill list <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-9-5 下午12:17 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> IdlingResource <-- ↓↓↓/////////////////////////////////////
    private boolean isRemoteFinished = false;

    public void fetchDataFromRemote() {
        // 模拟一个网络请求
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isRemoteFinished = true;
            }
        }.start();
    }

    public boolean isRemoteFinished(){
        return isRemoteFinished;
    }
    /////////////////////////////////////↑↑↑ --> IdlingResource <-- ↑↑↑/////////////////////////////////////
}
