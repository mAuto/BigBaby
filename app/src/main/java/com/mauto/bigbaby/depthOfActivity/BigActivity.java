package com.mauto.bigbaby.depthOfActivity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.mauto.bigbaby.R;

import java.util.ArrayList;
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

        Log.e("--> onCreate <--", "start");
        getFragmentWhos();
    }

    private void initViews() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl_container, BigContainerFragment.newInstance()).commit();
    }

    private void getFragmentWhos() {
        if (tmp == null) {
            Log.e("--> getFragmentWhos <--", "bundle is null");
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
        Log.e("--> onSaveInstanceState <--", "start");
        if (outState != null) {
            tmp = (Bundle) outState.clone();
        }
        getFragmentWhos();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("--> onRestoreInstanceState <--", "start");
        if (savedInstanceState != null) {
            tmp = (Bundle) savedInstanceState.clone();
        }
        getFragmentWhos();
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("--> onNewIntent <--", "HERE");
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        Log.e("--> onStart <--", "HERE");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("--> onResume <--", "HERE");
        super.onResume();
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> fragments = manager.getFragments();
    }

    @Override
    protected void onPause() {
        Log.e("--> onPause <--", "HERE");
        super.onPause();
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> fragments = manager.getFragments();
    }

    @Override
    protected void onStop() {
        Log.e("--> onStop <--", "HERE");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("--> onDestroy <--", "HERE");
        super.onDestroy();
    }
}
