package com.mauto.bigbaby.lab.depthOfActivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mauto.bigbaby.R;

import java.util.List;

public class BigActivity extends AppCompatActivity {

    Bundle tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            tmp = (Bundle) savedInstanceState.clone();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big);

        initViews();

        Log.e("--> BigActivity <--", "onCreate"+"");
        getFragmentWhos();
    }

    private void initViews() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl_container, BigContainerFragment.newInstance()).commit();
    }

    private void getFragmentWhos() {
        if (tmp == null) {
            Log.e("--> BigActivity <--", "getFragmentWhos"+": "+"bundle is null");
            return;
        }
        String[] fragmentWhos = tmp.getStringArray("android:support:request_fragment_who");

        if (fragmentWhos != null) {
            for (int i=0;i<fragmentWhos.length;i++) {
                Log.e("--> getFragmentWhos <--", "who:"+fragmentWhos[i]);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("--> BigActivity <--", "onSaveInstanceState"+"");
        if (outState != null) {
            tmp = (Bundle) outState.clone();
        }
        getFragmentWhos();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("--> BigActivity <--", "onRestoreInstanceState"+"");
        if (savedInstanceState != null) {
            tmp = (Bundle) savedInstanceState.clone();
        }
        getFragmentWhos();
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("--> BigActivity <--", "onNewIntent"+"");
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        Log.e("--> BigActivity <--", "onStart"+"");
        super.onStart();
    }

    int flag = 0;
    @Override
    protected void onResume() {
        Log.e("--> BigActivity <--", "onResume"+"");
        super.onResume();
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> fragments = manager.getFragments();
        if (flag > 0) {
            if (fragments.size() == 7) {
                manager.beginTransaction().remove(fragments.get(6)).commitNow();
//                manager.executePendingTransactions();
            }
        }
        fragments = manager.getFragments();
        flag += 1;
    }

    @Override
    protected void onPause() {
        Log.e("--> BigActivity <--", "onPause"+"");
        super.onPause();
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> fragments = manager.getFragments();
    }

    @Override
    protected void onStop() {
       Log.e("--> BigActivity <--", "onStop"+"");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("--> BigActivity <--", "onDestroy"+"");
        super.onDestroy();
    }
}
