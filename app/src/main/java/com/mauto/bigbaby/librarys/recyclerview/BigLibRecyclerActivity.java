package com.mauto.bigbaby.librarys.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.base.BigBaseActivity;
import com.mauto.bigbaby.router.RoutPointer;
import com.mauto.bigbaby.router.Router;

public class BigLibRecyclerActivity extends BigBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_recycler_activity_main);

        updateTitle("RecyclerView lib");
    }

    public void onClickSortedList(View view) {
        Router.jump(this, RoutPointer.LIB_RECYCLER_SORTED);
    }

    public void onClickDiffUtil(View view) {
        Router.jump(this, RoutPointer.DIFFUTILS);
    }

    public void onClickAsyncListUtil(View view) {
        Router.jump(this, RoutPointer.LIB_RECYCLER_ASYNC);
    }

    public void onClickThreadUtil(View view) {
    }

    public void onClickTileList(View view) {
    }
}
