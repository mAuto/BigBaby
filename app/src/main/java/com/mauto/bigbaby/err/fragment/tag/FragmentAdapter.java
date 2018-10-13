package com.mauto.bigbaby.err.fragment.tag;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

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
    public long getItemId(int position) {
        return ((BigErrFragment)mFragments.get(position)).getFragmentId();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return mFragments.indexOf(object);
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

        specifyFragmentId(fragments);

        notifyDataSetChanged();
    }

    private int mIdFlag = 0;
    private void specifyFragmentId(List<Fragment> fragments) {
        if (fragments == null || fragments.size() == 0)
            return;
        for (Fragment fragment : fragments) {
            ((BigErrFragment)fragment).setFragmentId(mIdFlag ++);
        }
    }

    private ViewGroup mContainer;
    public void specifyContainer(ViewGroup container) {
        mContainer = container;
    }
}
