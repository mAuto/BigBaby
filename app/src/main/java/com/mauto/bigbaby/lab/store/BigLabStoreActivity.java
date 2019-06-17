package com.mauto.bigbaby.lab.store;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mauto.bigbaby.R;
import com.tencent.mmkv.MMKV;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class BigLabStoreActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_store_main_activity);

        MMKV.initialize(this);
//        encodeValues();

//        loadAllValues();

//        SharedPreferences sp_0 = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences sp_1 = PreferenceManager.getDefaultSharedPreferences(this);
//
//        Log.e(">>> BigLabStoreActivity", "onCreate@37 --> " + " " + sp_0.toString());
//        Log.e(">>> BigLabStoreActivity", "onCreate@38 --> " + " " + sp_1.toString());

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void encodeValues (){

        MMKV store = MMKV.defaultMMKV();
        store.putBoolean("bol_0", true);
        store.putFloat("fl_0", 0);
        store.putInt("int_0", 1);
        store.putString("str_0", "这是一个针对MMKV获取全部值的转换测试，用来替换现有的本地存储核心代码");
//        store.putStringSet("strset_0", (Set<String>) Arrays.asList("alsjfda", "slda;l", "alskdfja;sk", "alkdflajsflajf", "alskfhaohfpow", "aksdjaljd"));

        store.putBoolean("bol_1", true);
        store.putFloat("fl_1", 0);
        store.putInt("int_1", 1);
        store.putString("str_1", "这是一个针对MMKV获取全部值的转换测试，用来替换现有的本地存储核心代码");
//        store.putStringSet("strset_1", (Set<String>) Arrays.asList("alsjfda", "slda;l", "alskdfja;sk", "alkdflajsflajf", "alskfhaohfpow", "aksdjaljd"));

        store.putBoolean("bol_2", true);
        store.putFloat("fl_2", 0);
        store.putInt("int_2", 1);
        store.putString("str_2", "这是一个针对MMKV获取全部值的转换测试，用来替换现有的本地存储核心代码");
//        store.putStringSet("strset_2", (Set<String>) Arrays.asList("alsjfda", "slda;l", "alskdfja;sk", "alkdflajsflajf", "alskfhaohfpow", "aksdjaljd"));

        store.putBoolean("bol_3", true);
        store.putFloat("fl_3", 0);
        store.putInt("int_3", 1);
        store.putString("str_3", "这是一个针对MMKV获取全部值的转换测试，用来替换现有的本地存储核心代码");
//        store.putStringSet("strset_3", (Set<String>) Arrays.asList("alsjfda", "slda;l", "alskdfja;sk", "alkdflajsflajf", "alskfhaohfpow", "aksdjaljd"));

        store.putBoolean("bol_4", true);
        store.putFloat("fl_4", 0);
        store.putInt("int_4", 1);
        store.putString("str_4", "这是一个针对MMKV获取全部值的转换测试，用来替换现有的本地存储核心代码");
//        store.putStringSet("strset_4", (Set<String>) Arrays.asList("alsjfda", "slda;l", "alskdfja;sk", "alkdflajsflajf", "alskfhaohfpow", "aksdjaljd"));
    }

    Map<String, Object> mMap;
    private void loadAllValues (){

        mMap = new HashMap<>();

        MMKV store = MMKV.defaultMMKV();
        String[] allKeys = store.allKeys();

        Log.e(">>> BigLabStoreActivity", "loadAllValues@75 --> " + " " +store.getString("str_4", "test"));

//        if (allKeys != null && allKeys.length > 0) {
//            for (String key : allKeys) {
//                mMap.put(key, toObject(store.decodeBytes(key)));
//            }
//        }

        mMap.put("bol_3", store.getBoolean("bol_3", false));
        mMap.put("fl_3", store.getFloat("fl_3", 0));
        mMap.put("int_3", store.getInt("int_3", 0));
        mMap.put("str_3", store.getString("str_3", ""));

        boolean bol_3 = (boolean) mMap.get("bol_3");

        Log.e(">>> BigLabStoreActivity", "loadAllValues@81 --> " + " " + mMap.size() + "bol_3:" + bol_3);
    }

    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public Object toObject (byte[] bytes) {
        Object obj = null;

        if (bytes == null)
            return obj;

        Log.e(">>> BigLabStoreActivity", "toObject@103 --> " + " " +bytes.toString());

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            Log.e(">>> BigLabStoreActivity", "toObject@83 --> " + " " + ex.toString());
        } catch (ClassNotFoundException ex) {
            Log.e(">>> BigLabStoreActivity", "toObject@86 --> " + " " + ex.toString());
        }
        return obj;
    }



    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (ProtobufPreferences.manage().hasMigratedData(name))
            return ProtobufPreferences.manage().of(name, mode);
        else
            return ProtobufPreferences.manage().of(name, mode, super.getSharedPreferences(name, mode));
    }




    SharedPreferences mPreferences = null;

    public void onClickAddInt(View view) {
        mPreferences.edit()
                .putInt(String.valueOf(System.currentTimeMillis()), 10)
                .putInt(String.valueOf(System.currentTimeMillis()), 10)
                .commit();
    }

    public void onClickAddLong(View view) {
        mPreferences.edit().putLong(String.valueOf(System.currentTimeMillis()), 11).commit();
    }

    public void onClickAddFloat(View view) {
        mPreferences.edit().putFloat(String.valueOf(System.currentTimeMillis()), 12).apply();
    }

    public void onClickAddString(View view) {
        mPreferences.edit().putString(String.valueOf(System.currentTimeMillis()), "13").commit();
    }

    public void onClickAddBoolean(View view) {
        mPreferences.edit().putBoolean(String.valueOf(System.currentTimeMillis()), false).apply();
    }

    public void onClickContains(View view) {
        mPreferences.contains(String.valueOf(System.currentTimeMillis()));
    }

    public void onClickRemove(View view) {
        mPreferences.edit().remove(String.valueOf(System.currentTimeMillis())).commit();
    }

    public void onClickClear(View view) {
        mPreferences.edit().clear();
    }

    public void onClickGetAll(View view) {
        Map<String, ?> allEntry =  mPreferences.getAll();

        Log.e(">>> BigLabStoreActivity", "onClickGetAll@182 --> " + "total："+allEntry.size());

        for (String key : allEntry.keySet()) {
            Log.e(">>> BigLabStoreActivity", key + " -> " + String.valueOf(allEntry.get(key)));
        }
    }

    public void onClickRegister(View view) {
        mPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void onClickUnregister(View view) {
        mPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.e(">>> BigLabStoreActivity", "onSharedPreferenceChanged@184 --> " + " " + key);
    }
}
