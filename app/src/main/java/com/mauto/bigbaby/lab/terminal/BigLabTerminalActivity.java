package com.mauto.bigbaby.lab.terminal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mauto.bigbaby.R;

public class BigLabTerminalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_terminal_main_activity);

        Terminal.getInstance().addTerminal(this);
    }
}
