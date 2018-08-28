package com.mauto.bigbaby.err;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.router.RoutPointer;
import com.mauto.bigbaby.router.Router;

public class BigErrMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.err_main_activity);
    }

    public void onClickErrFragment(View view) {
        Router.jump(this, RoutPointer.ERR_FRAGMENT);
    }
}
