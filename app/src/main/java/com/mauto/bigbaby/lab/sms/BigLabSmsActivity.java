package com.mauto.bigbaby.lab.sms;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mauto.bigbaby.R;

public class BigLabSmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_lab_sms);
    }

    private void insertSms() {


        Thread t = new Thread(){
            @Override
            public void run() {
                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                values.put("address", 95555);
                values.put("type", 1);
                values.put("date", System.currentTimeMillis());
                cr.insert(Uri.parse("content://sms"), values);
            }
        };
        t.start();
    }

    private void readSmsDB() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://sms"), new String[]{"address", "date", "body", "type"},
                null, null, null);
        while(cursor.moveToNext()){
            String address = cursor.getString(0);
            long date = cursor.getLong(1);
            if ("95555".equals(address) && date == 1549960979000l) {
                String body = cursor.getString(2);
                String type = cursor.getString(3);

                Log.e(">>>>>>", "BigLabSmsActivity --> " + "readSmsDB"+" date:"+date);
                Log.e(">>>>>>", "BigLabSmsActivity --> " + "readSmsDB"+" type:"+type);
                Log.e(">>>>>>", "BigLabSmsActivity --> " + "readSmsDB"+" body:"+body);

                insertSms();
//                getContentResolver().up`date()
            }
        }
    }

    public void onClickInsert(View view) {
//        insertSms();
        new Thread() {
            @Override
            public void run() {
//                readSmsDB();
                insertSms();
            }
        }.start();
    }
}
