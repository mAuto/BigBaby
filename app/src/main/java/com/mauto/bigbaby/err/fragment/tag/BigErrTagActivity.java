package com.mauto.bigbaby.err.fragment.tag;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.err.fragment.tag.BigErrFragment;
import com.mauto.bigbaby.err.fragment.tag.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class BigErrTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.err_fragment_activity);

        initViews();
    }

    private ViewPager mVpFragments;
    private List<Fragment> mFragments;
    private FragmentAdapter mAdapter;
    private void initViews(){
        mFragments = new ArrayList<>();
        mVpFragments = findViewById(R.id.vp_fragments);
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mVpFragments.setAdapter(mAdapter);
        mVpFragments.setOffscreenPageLimit(10);

        mFragments.add(BigErrFragment.newInstance("feature:0"));
        mFragments.add(BigErrFragment.newInstance("feature:1"));
        mFragments.add(BigErrFragment.newInstance("feature:2"));
        mAdapter.setFragments(mFragments, 0);
        mAdapter.specifyContainer(mVpFragments);
    }

    public void onClickAddFragments(View view) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BigErrFragment.newInstance("general:0"));
        fragments.add(BigErrFragment.newInstance("general:1"));
        fragments.add(BigErrFragment.newInstance("general:2"));
        fragments.add(BigErrFragment.newInstance("general:3"));
        fragments.add(BigErrFragment.newInstance("general:4"));
        mAdapter.setFragments(fragments, 2);

//        mVpFragments.setCurrentItem(0, true);
    }
}