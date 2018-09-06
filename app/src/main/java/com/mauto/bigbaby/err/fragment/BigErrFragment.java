package com.mauto.bigbaby.err.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("--> err/fragment <--", "onCreate");
    }

    String label;

    private TextView mTvIndex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.err_fragment, container, false);

        mTvIndex = view.findViewById(R.id.tv_index);
        label = getArguments().getString("label");
        Log.e("--> err/fragment <--", label+" => "+this.toString());
        mTvIndex.setText(label);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("--> err/fragment <--", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("--> err/fragment <--", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("--> err/fragment <--", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("--> err/fragment <--", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("--> err/fragment <--", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("--> err/fragment <--", label+" => "+"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("--> err/fragment <--", label+" => "+"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("--> err/fragment <--", "onDetach");
    }

    public static BigErrFragment newInstance(String label) {

        Bundle args = new Bundle();
        args.putString("label", label);
        BigErrFragment fragment = new BigErrFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("--> err/fragment <--", "tag => "+this.getTag());
    }
}
