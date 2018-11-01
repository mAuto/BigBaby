package com.mauto.bigbaby.depthOfActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohuidong on 18-10-30.
 */

public class BigFragmentAdapter extends FragmentPagerAdapter {

    public BigFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments == null)
            mFragments = new ArrayList<>();
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if (mFragments == null)
            mFragments = new ArrayList<>();
        return mFragments.size();
    }

    private List<Fragment> mFragments;
    public void setFragments(List<Fragment> fragments) {
        if (fragments == null || fragments.size() == 0)
            return;

        if (mFragments == null)
            mFragments = new ArrayList<>();

        mFragments.clear();
        mFragments.addAll(fragments);
        notifyDataSetChanged();
    }
}
