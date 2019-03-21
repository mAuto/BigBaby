package com.mauto.bigbaby.lab.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;

public class BigLabToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_toast_main);
    }

    public void onClickInteractiveToast(View view) {
        ToastUtils.showInteractiveToast(this, "test", null);
    }
}
