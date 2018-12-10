package com.mauto.bigbaby.librarys.recyclerview.DiffUtil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.base.BigBaseActivity;

import static com.mauto.bigbaby.markdown.MarkdownPointer.MD_LINK_LIB_RECYCLER_DIFFUTIL;

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
