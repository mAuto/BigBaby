package com.mauto.bigbaby.err.fragment.attach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;

public class BigErrAttachActivity extends AppCompatActivity {

    private BigErrAttachFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.err_attach_activity);

        mFragment = BigErrAttachFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(mFragment, "err_attach").commit();
    }

    /////////////////////////////////////////--> 18-9-13 下午3:34 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ -->  <-- ↓↓↓/////////////////////////////////////
    public void onClickTest(View view) {
        mFragment.function();
    }
    /////////////////////////////////////↑↑↑ -->  <-- ↑↑↑/////////////////////////////////////
}
