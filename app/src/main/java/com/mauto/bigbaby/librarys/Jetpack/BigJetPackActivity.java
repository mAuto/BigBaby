package com.mauto.bigbaby.librarys.Jetpack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.support.router.RoutPointer;
import com.mauto.bigbaby.support.router.Router;
import com.mauto.bigbaby.support.router.RouterMap;

import okhttp3.Route;

public class BigJetPackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_jet_pack_main_activity);
    }

    public void onClickLifecycle(View view) {
        Router.jump(this, RoutPointer.LIB_JETPACK_LIFECYCLE);
    }
}
