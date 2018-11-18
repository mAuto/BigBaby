package com.mauto.bigbaby.err.fragment.attach;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mauto.bigbaby.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BigErrAttachFragment extends Fragment {

    private Context mContext;

    public BigErrAttachFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.err_attach_fragment, container, false);
    }

    public static BigErrAttachFragment newInstance() {
        Bundle args = new Bundle();

        BigErrAttachFragment fragment = new BigErrAttachFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /////////////////////////////////////////--> 18-9-12 下午2:58 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> err fun <-- ↓↓↓/////////////////////////////////////
    public void function() {
        Log.e("--> attach <--", "Context is null? --> "+String.valueOf(mContext == null ? true : false));
    }
    /////////////////////////////////////↑↑↑ --> err fun <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-9-12 下午2:55 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> lifecycle <-- ↓↓↓/////////////////////////////////////
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    /////////////////////////////////////↑↑↑ --> lifecycle <-- ↑↑↑/////////////////////////////////////
}
