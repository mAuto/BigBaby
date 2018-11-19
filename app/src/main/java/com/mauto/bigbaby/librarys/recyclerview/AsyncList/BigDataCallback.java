package com.mauto.bigbaby.librarys.recyclerview.AsyncList;

import android.support.v7.util.AsyncListUtil;

/**
 * Created by haohuidong on 18-11-16.
 */

public class BigDataCallback extends AsyncListUtil.DataCallback<String> {
    @Override
    public int refreshData() {
        return 200;
    }

    @Override
    public void fillData(String[] data, int startPosition, int itemCount) {

        for (int i=0;i<itemCount;i++) {
            data[i] = "Item: " + (startPosition + i + 1);
        }

        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {}

    }
}
