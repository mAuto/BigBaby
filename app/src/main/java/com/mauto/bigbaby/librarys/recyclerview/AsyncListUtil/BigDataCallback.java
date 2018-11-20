package com.mauto.bigbaby.librarys.recyclerview.AsyncListUtil;

import android.support.v7.util.AsyncListUtil;
import android.util.Log;

/**
 * Created by haohuidong on 18-11-16.
 */

public class BigDataCallback extends AsyncListUtil.DataCallback<String> {
    @Override
    public int refreshData() {
        Log.e("--> BigDataCallback <--", "refreshData"+"");
        // 返回列表长度
        /**
         * 调用栈：AsyncListUtil construction method
         * -> AsyncListUtil.refresh()
         * -> ThreadUtil.BackgroundCallback.refresh()
         * -> AsyncListUtil.DataCallback.refreshData
         * */
        return 100;
    }

    @Override
    public void fillData(String[] data, int startPosition, int itemCount) {

        Log.e("--> BigDataCallback <--", "fillData"+" data_size:"+data.length+" start_postion:"+startPosition+" item_count:"+itemCount);

        // 填充数据
        for (int i=0;i<itemCount;i++) {
            data[i] = "Item: " + (startPosition + i + 1);
        }

        // 暂停一秒模拟取数据库耗时
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {}
    }
}
