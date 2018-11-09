package com.mauto.bigbaby.share;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mauto.bigbaby.R;

public class BigShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_main);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getAction() == Intent.ACTION_VIEW) {
                Uri uri = intent.getData();
                Log.e("--> BigBabay <--", "share uri: "+uri.toString());
            }
        }
    }
}
