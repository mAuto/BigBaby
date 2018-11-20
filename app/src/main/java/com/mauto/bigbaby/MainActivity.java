package com.mauto.bigbaby;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.markdown.MarkdownPointer;
import com.mauto.bigbaby.router.RoutPointer;
import com.mauto.bigbaby.router.Router;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void onClickDiffUtils(View view) {
        Router.jump(this, RoutPointer.DIFFUTILS);
    }


    public void onClickErr(View view) {
        Router.jump(this, RoutPointer.ERR_MAIN);
    }

    public void onClickLiveData(View view) {
        Router.jump(this, RoutPointer.LIVEDATA);
    }

    public void onClickUT(View view) {
        Router.jump(this, RoutPointer.UT_MAIN);
    }

    public void onClickThread(View view) {
        Router.jump(this, RoutPointer.THREAD);
    }

    public void onClickLibraries(View view) {
        Router.jump(this, RoutPointer.LIB_MAIN);
    }

    public void onClickAlgorithm(View view) {
    }

    public void onClickFreeNote(View view) {
        Uri uri = Uri.parse(MarkdownPointer.buildUri("https://github.com/mAuto/BigBaby/blob/master/app/src/main/java/com/mauto/bigbaby/note/note.md"));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
