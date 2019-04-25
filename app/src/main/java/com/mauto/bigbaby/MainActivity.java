package com.mauto.bigbaby;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.support.markdown.MarkdownPointer;
import com.mauto.bigbaby.support.router.RoutPointer;
import com.mauto.bigbaby.support.router.Router;
import com.mauto.bigbaby.support.router.RouterMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("", ""));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }



    public void onClickLiveData(View view) {
        Router.jump(this, RoutPointer.LIVEDATA);
    }

    public void onClickLibraries(View view) {
        Router.jump(this, RoutPointer.LIB_MAIN);
    }


    /////////////////////////////////////////--> 18-12-18 下午5:47 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Algorithm <-- ↓↓↓/////////////////////////////////////
    public void onClickLinearList(View view) {
        Router.jump(this, RoutPointer.ALGORITHM_LINEAR_LIST);
    }

    public void onClickElse(View view) {
        Router.jump(this, RoutPointer.ALGORITHM_ELSE);
    }
    /////////////////////////////////////↑↑↑ --> Algorithm <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-18 下午5:47 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Architecture <-- ↓↓↓/////////////////////////////////////
    public void onClickEasyTest(View view) {
    }

    public void onClickMVI(View view) {
    }
    /////////////////////////////////////↑↑↑ --> Architecture <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-18 下午5:48 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Err <-- ↓↓↓/////////////////////////////////////
    public void onClickFragment(View view) {
        Router.jump(this, RoutPointer.ERR_FRAGMENT);
    }
    /////////////////////////////////////↑↑↑ --> Err <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-18 下午6:53 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Lab <-- ↓↓↓/////////////////////////////////////
    public void onClickThread(View view) {
        Router.jump(this, RoutPointer.THREAD);
    }

    public void onClickTarget26(View view) {
        Router.jump(this, RoutPointer.TARGET26_SERVICE);
    }

    public void onClickOverlay(View view) {
        Router.jump(this, RoutPointer.LAB_PERMISSION);
    }

    public void onClickSms(View view) {
        Router.jump(this, RoutPointer.LAB_SMS);
    }

    public void onClickTerminal(View view) {
        Router.jump(this, RoutPointer.LAB_TERMINAL);
    }

    public void onClickConcurrent(View view) {
        Router.jump(this, RoutPointer.LAB_CONCURRENT);
    }

    public void onClickDialogQueue(View view) {
        Router.jump(this, RoutPointer.LAB_DIALOG_QUEUE);
    }
    /////////////////////////////////////↑↑↑ --> Lab <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-18 下午6:53 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Libraries <-- ↓↓↓/////////////////////////////////////
    public void onClickJetpack(View view) {
    }

    public void onClickRecycler(View view) {
        Router.jump(this, RoutPointer.LIB_RECYCLER_MAIN);
    }
    /////////////////////////////////////↑↑↑ --> Libraries <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-18 下午6:54 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Software test <-- ↓↓↓/////////////////////////////////////
    public void onClickUT(View view) {
        Router.jump(this, RoutPointer.UT_MAIN);
    }
    /////////////////////////////////////↑↑↑ --> Software test <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-18 下午6:55 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Note <-- ↓↓↓/////////////////////////////////////
    public void onClickFreeNote(View view) {
        Router.jump(this, RoutPointer.NOTE_MAIN);
    }

    public void onClickTODO(View view) {
    }

    public void onClickAnim(View view) {
        Router.jump(this, RoutPointer.LAB_ANIM);
    }
    /////////////////////////////////////↑↑↑ --> Note <-- ↑↑↑/////////////////////////////////////

}
