package com.mauto.bigbaby.lab.popup;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mauto.bigbaby.R;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class BigLabPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_popub_mian_activity);

        new SyncDialog.Builder(this).setTitle("Tip:")
                .setMessage("This is a test popup")
                .setPositiveButton("I know", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Bye", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }
}
