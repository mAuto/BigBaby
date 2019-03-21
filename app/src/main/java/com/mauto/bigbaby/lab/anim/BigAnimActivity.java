package com.mauto.bigbaby.lab.anim;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.lab.anim.widgets.fireworks.Firecracker;
import com.mauto.bigbaby.lab.anim.widgets.shinebutton.ShineButton;

public class BigAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_anim2);

        initViews();
    }

    Handler mAnimHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int targetWhat = msg.what;
            int targetDuration = 100;

            switch(msg.what) {
                case 0:{
                    mFCracker2.startAnim();
                    targetWhat = 1;
                   targetDuration = 200;
                }break;
                case 1:{
                    mFCracker1.startAnim();
                    targetWhat = 2;
                    targetDuration = 200;
                }break;
                case 2:{
                    mSbShine1.showAnim();
                    targetWhat = 3;
                    targetDuration = 100;
                }break;
                case 3:{
                    mFCracker0.startAnim();;
                    targetWhat = 4;
                    targetDuration = 200;
                }break;
                case 4:{
                    mSbShine0.showAnim();
                    targetWhat = 5;
                    targetDuration = 300;
                }break;
                case 5:{
                    mSbShine2.showAnim();
                    targetWhat = 0;
                    targetDuration = 1000;
                }break;
            }
            sendEmptyMessageDelayed(targetWhat, targetDuration);
        }
    };

    private ShineButton mSbShine0, mSbShine1, mSbShine2;
    private Firecracker mFCracker0, mFCracker1, mFCracker2;
    private void initViews() {
        mSbShine0 = findViewById(R.id.sb_shine_0);
        mFCracker0 = findViewById(R.id.f_cracker_0);
        mSbShine1 = findViewById(R.id.sb_shine_1);
        mFCracker1 = findViewById(R.id.f_cracker_1);
        mSbShine2 = findViewById(R.id.sb_shine_2);
        mFCracker2 = findViewById(R.id.f_cracker_2);

        mAnimHandler.sendEmptyMessageDelayed(0, 1000);
    }

}
