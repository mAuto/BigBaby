package com.mauto.bigbaby.depthOfActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mauto.bigbaby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohuidong on 18-10-30.
 */

public class BigContainerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_container, null);
        initViews(view);
        return view;
    }

    private ViewPager mVpContainer;
    private BigFragmentAdapter mAdapter;
    private void initViews(View view) {
        mVpContainer = view.findViewById(R.id.vp_container);
        mVpContainer.setOffscreenPageLimit(10);
        mAdapter = new BigFragmentAdapter(getActivity().getSupportFragmentManager());
        mVpContainer.setAdapter(mAdapter);

        initFragments();

        mAdapter.setFragments(mFragment);
    }

    private List<Fragment> mFragment;
    private void initFragments() {
        if (mFragment == null)
            mFragment = new ArrayList<>();
        mFragment.clear();
        for (int i=0;i<5;i++) {
            mFragment.add(BigChildFragment.newInstance(i));
        }
    }

    public static BigContainerFragment newInstance() {

        Bundle args = new Bundle();

        BigContainerFragment fragment = new BigContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
