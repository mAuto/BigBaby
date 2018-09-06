package com.mauto.bigbaby.err.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

    private FragmentManager mManager;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        mManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments == null)
        return null;
        else {
            Fragment tmp = mFragments.get(position);
//            if (mManager != null && mManager.findFragmentByTag(tmp.getTag()) != null){
//                mManager.beginTransaction().remove(tmp).commit();
//                mManager.executePendingTransactions();
//            }
            return tmp;
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
            for (int i = index; i<mFragments.size(); i++) {
                Fragment fragment = mFragments.get(i);
                if (mManager != null && mManager.findFragmentByTag(fragment.getTag()) != null && !fragment.isDetached()){
                    mManager.beginTransaction().remove(fragment).commit();
                    mManager.executePendingTransactions();
                }
            }
            mFragments.addAll(index, fragments);
        }

        notifyDataSetChanged();
    }
}
