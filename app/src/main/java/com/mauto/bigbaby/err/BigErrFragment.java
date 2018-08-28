package com.mauto.bigbaby.err;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauto.bigbaby.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BigErrFragment extends Fragment {


    public BigErrFragment() {
        // Required empty public constructor
    }

    private TextView mTvIndex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.err_fragment, container, false);

        mTvIndex = view.findViewById(R.id.tv_index);
        String label = getArguments().getString("label");
        mTvIndex.setText(label);
        return view;
    }

    public static BigErrFragment newInstance(String label) {

        Bundle args = new Bundle();
        args.putString("label", label);
        BigErrFragment fragment = new BigErrFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
