package com.mauto.bigbaby.ut;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohuidong on 18-8-24.
 */

public class BigUnitTestSample {

    public String appendString(String a, String b) {
        if (TextSys.isEmpty(a) || TextSys.isEmpty(b))
            return null;

        String result = new StringBuffer(a).append(b).toString();

        if (mPrinter != null){
            mPrinter.print(result);
        }

        return result;
    }

    public void appendStringWithoutReturn(String a, String b) {
        if (TextSys.isEmpty(a) || TextSys.isEmpty(b))
            return;

        String result = new StringBuffer(a).append(b).toString();

        LogSys.print(result);

        return;
    }

    private List<String> targetData;
    private NetHunter mHunter;

    public String getDataByPos(int pos) {
        // something operation

        // 非常耗时的操作，获取数据
        mHunter.fetchData(targetData);

        return targetData.get(pos);
    }

    public void addMockNetHunter(NetHunter hunter) {
        mHunter = hunter;
    }

    public void addMockList(List<String> list){
        targetData = list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("obj is null");
        return super.equals(obj);
    }

    private Printer mPrinter = null;
    public void addPrinter(Printer printer) {
        mPrinter = printer;
    }
}
