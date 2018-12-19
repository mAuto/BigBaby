package com.mauto.bigbaby.lab.permission;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mauto.bigbaby.R;

import java.lang.reflect.Field;
import java.util.List;

public class BigLabPerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_lab_per);


    }

    public void getAllAppNames(){
        PackageManager pm=getPackageManager();
        //PackageManager.GET_UNINSTALLED_PACKAGES==8192
        List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        //PackageManager.GET_SHARED_LIBRARY_FILES==1024
        //List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_SHARED_LIBRARY_FILES);
        //PackageManager.GET_META_DATA==128
        //List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_META_DATA);
        //List<PackageInfo> list2=pm.getInstalledPackages(0);
        //List<PackageInfo> list2=pm.getInstalledPackages(-10);
        //List<PackageInfo> list2=pm.getInstalledPackages(10000);
        for (PackageInfo packageInfo : list2) {
            //得到手机上已经安装的应用的名字,即在AndriodMainfest.xml中的app_name。
            String appName=packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            //得到应用所在包的名字,即在AndriodMainfest.xml中的package的值。
            String packageName=packageInfo.packageName;
            Log.e("### HHD", "\n***************************");
            Log.e("### HHD", "应用的名字:"+appName);
            Log.e("### HHD", "应用的包名字:"+packageName);
            Log.e("### HHD", "***************************");
        }
    }

    private void jumpToOverlay(){
        getAllAppNames();
        try {
            Class clazz = Settings.class;
            Field field = clazz.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");

            Intent intent = new Intent(field.get(null).toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("package:" + "com.android.vending"));
            this.startActivityForResult(intent, 1002);
        } catch (Exception e) {

        }
    }

    public void onClickJump(View view) {
        jumpToOverlay();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == 1002) {
            Log.e(">>>>>>", "BigLabPerActivity --> " + "onActivityResult"+"");
        }
        
    }
}
