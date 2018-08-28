package com.mauto.bigbaby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.mauto.bigbaby.router.RoutPointer;
import com.mauto.bigbaby.router.Router;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDiffUtils(View view) {
        Router.jump(this, RoutPointer.DIFFUTILS);
    }

<<<<<<< HEAD
    public void onClickErr(View view) {
        Router.jump(this, RoutPointer.ERR_MAIN);
=======
    public void onClickLiveData(View view) {
        Router.jump(this, RoutPointer.LIVEDATA);
>>>>>>> c131c4a4cd5d29fd3a92b8eaad9f38872be72c0b
    }
}
