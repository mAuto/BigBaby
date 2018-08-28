package com.mauto.bigbaby.err;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments == null)
        return null;
        else {
            return mFragments.get(position);
        }
    }

    @Override
    public int getCount() {
        if (mFragments == null)
            return 0;
        else return mFragments.size();
    }

    private List<Fragment> mFragments;
    public void setFragments(List<Fragment> fragments, int index) {
        if (fragments == null || fragments.size() == 0)
            return;

        if (mFragments == null)
            mFragments = new ArrayList<>();

        if (mFragments.size() == 0) {
            mFragments.addAll(fragments);
        }else {
            mFragments.addAll(index, fragments);
        }

        notifyDataSetChanged();
    }
}
