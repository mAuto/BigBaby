package com.mauto.bigbaby.lab.depthOfActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("--> BigContainerFragment <--", "onAttach"+"");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("--> BigContainerFragment <--", "onCreate"+": "+this.toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("--> BigContainerFragment <--", "onCreateView"+": "+toString());
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.e("--> BigContainerFragment <--", "onSaveInstanceState"+": "+this.toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.e("--> BigContainerFragment <--", "onViewStateRestored"+": "+this.toString());
        if (savedInstanceState != null)
            Log.e("--> BigContainerFragment <--", "onViewStateRestored"+": "+savedInstanceState.toString());
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.e("--> BigContainerFragment <--", "onDestroy"+": "+this.toString());
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.e("--> BigContainerFragment <--", "onDestroyView"+": "+this.toString());
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.e("--> BigContainerFragment <--", "onDetach"+": "+this.toString());
        super.onDetach();
    }

    @Override
    public void onPause() {
        Log.e("--> BigContainerFragment <--", "onPause"+": "+this.toString());
        super.onPause();
        List<Fragment> fragments = mAdapter.getFragments();
        int currentItemPos = mVpContainer.getCurrentItem();
    }

    @Override
    public void onStop() {
        Log.e("--> BigContainerFragment <--", "onStop"+": "+this.toString());
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.e("--> BigContainerFragment <--", "onResume"+": "+this.toString());
        super.onResume();
        List<Fragment> fragments = mAdapter.getFragments();
        int currentItemPos = mVpContainer.getCurrentItem();
    }

    @Override
    public void onStart() {
        Log.e("--> BigContainerFragment <--", "onStart"+": "+this.toString());
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("--> BigContainerFragment <--", "onActivityCreated"+": "+this.toString());
        super.onActivityCreated(savedInstanceState);
    }
}
