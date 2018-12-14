package com.mauto.bigbaby.err.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.support.router.RoutPointer;
import com.mauto.bigbaby.support.router.Router;

public class BigErrFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.err_tag_activity);
    }

    public void onClickErrFragment(View view) {
        Router.jump(this, RoutPointer.ERR_TAG);
    }

    public void onClickErrFragmentAttach(View view) {
        Router.jump(this, RoutPointer.ERR_ATTACH);
    }
}
