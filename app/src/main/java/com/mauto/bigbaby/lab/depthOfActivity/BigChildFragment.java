package com.mauto.bigbaby.lab.depthOfActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauto.bigbaby.R;

import java.util.List;

/**
 * Created by haohuidong on 18-10-24.
 */

public class BigChildFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int pos = args.getInt("pos");
        View view = inflater.inflate(R.layout.activity_fragment_child, container, false);
        TextView tvTip = view.findViewById(R.id.tv_tip);
        tvTip.setOnClickListener(this);
        tvTip.setText("pos -> "+pos);
        return view;
    }

    public static BigChildFragment newInstance(int pos) {
        Bundle args = new Bundle();
        args.putInt("pos", pos);
        BigChildFragment fragment = new BigChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tip:{
                FragmentManager manager = getActivity().getSupportFragmentManager();
                List<Fragment> fragments = manager.getFragments();
            }break;
        }
    }
}
