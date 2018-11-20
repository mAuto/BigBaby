package com.mauto.bigbaby.librarys.recyclerview.SortedList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.librarys.recyclerview.BigRecyclerDataBuilder;

public class BigSortedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_recycler_sorted_main);

        initViews();
    }

    /////////////////////////////////////////--> 18-11-2 上午11:13 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> init views <-- ↓↓↓/////////////////////////////////////
    private RecyclerView mRvSortedList;
    private BigSortedAdapter mAdapter;
    private EditText mEtInput;

    private void initViews() {
        mRvSortedList = findViewById(R.id.rv_list);
        mRvSortedList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BigSortedAdapter();

        SortedListCallback callback = new SortedListCallback(mAdapter);
        SortedList sortedList = new SortedList(ViolinComposition.class, callback);
        mAdapter.bindSortedList(sortedList);

        mRvSortedList.setAdapter(mAdapter);
        mAdapter.setData(BigRecyclerDataBuilder.buildSortedCompositions());

        mEtInput = findViewById(R.id.et_input);
    }
    /////////////////////////////////////↑↑↑ --> init views <-- ↑↑↑/////////////////////////////////////

    public void onClickFillData(View view) {

        if (mEtInput.getText() != null) {
            String tmpContent = mEtInput.getText().toString().trim();
            mAdapter.insertData(Integer.valueOf(tmpContent));
        }

    }
}
