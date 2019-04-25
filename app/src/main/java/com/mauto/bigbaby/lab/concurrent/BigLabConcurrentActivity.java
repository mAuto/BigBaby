package com.mauto.bigbaby.lab.concurrent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mauto.bigbaby.R;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class BigLabConcurrentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_concurrent_main_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDeviceInfo();
    }

    //region >>> get device info
    /*
    * 19-3-26 下午5:19
    * */
    /////////////////////////////////////↓↓↓ --> interfaces <-- ↓↓↓/////////////////////////////////////
    private void getDeviceInfo() {

        int availableCores = getAvailableCores();
        int totalCores = getTotalCores();
        Log.e(">>>>>>", "BigLabConcurrentActivity.29 --> " + "getDeviceInfo"+" available:"+availableCores);
        Log.e(">>>>>>", "BigLabConcurrentActivity.29 --> " + "getDeviceInfo"+" total:"+totalCores);
    }

    private int getAvailableCores() {
        int availableCpu = Runtime.getRuntime().availableProcessors();
        return availableCpu;
    }

    public static int getTotalCores() {
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                if(Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            return files.length;
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    /////////////////////////////////////↑↑↑ --> interfaces <-- ↑↑↑/////////////////////////////////////
    //endregion <<< get device info

    //region >>> sample
    /*
    * 19-3-29 下午4:51
    * */
    /////////////////////////////////////↓↓↓ --> sample <-- ↓↓↓/////////////////////////////////////
    int a = 0, b = 0;
    int x = b, y =a;
    private void sampleFun0() {

        a = 0; b = 0;
        x = b; y =a;

        new Thread(){
            @Override
            public void run() {
                b = 2;
                y = a;
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        }.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(">>>>>>", "BigLabConcurrentActivity.96 --> " + "run"+" X:"+x + " Y:" + y);
            }
        }, 1000);
    }

    int a1 = 0;
    volatile boolean flag = false;
    private void sampleFun1() {
        new Thread(){
            @Override
            public void run() {
                Log.e(">>>>>>", "BigLabConcurrentActivity.111 --> " + "run"+"");
                a1 = 1;
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    Log.e(">>>>>>", "BigLabConcurrentActivity.116 --> " + "run"+" "+e.toString());
                }
                flag = true;
            }
        }.start();
        
        new Thread(){
            @Override
            public void run() {
                Log.e(">>>>>>", "BigLabConcurrentActivity.119 --> " + "run"+" "+flag);
                if (flag) {
                    Log.e(">>>>>>", "BigLabConcurrentActivity.120 --> " + "run"+"");
                    int i = a1;
                    Log.e(">>>>>>", "BigLabConcurrentActivity.122 --> " + "run"+" "+i);
                }
            }
        }.start();
    }
    
    public void onClickSampleFun(View view) {
        sampleFun1();
    }
    /////////////////////////////////////↑↑↑ --> sample <-- ↑↑↑/////////////////////////////////////
    //endregion <<< sample


}
