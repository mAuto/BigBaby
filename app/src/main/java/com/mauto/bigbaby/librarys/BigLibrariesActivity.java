package com.mauto.bigbaby.librarys;

import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.support.base.BigBaseActivity;
import com.mauto.bigbaby.router.RoutPointer;
import com.mauto.bigbaby.router.Router;

public class BigLibrariesActivity extends BigBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.libraries_activity_main);

        updateTitle("Libraries");
    }

    public void onClickRecyclerView(View view) {
        Router.jump(this, RoutPointer.LIB_RECYCLER_MAIN);
    }
}
