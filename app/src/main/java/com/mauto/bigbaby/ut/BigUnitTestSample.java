package com.mauto.bigbaby.ut;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;

/**
 * Created by haohuidong on 18-8-24.
 */

public class BigUnitTestSample {

    public String appendString(String a, String b) {
        if (TextSys.isEmpty(a) || TextSys.isEmpty(b))
            return null;

        String result = new StringBuffer(a).append(b).toString();

        if (mPrinter != null && mInputer != null){
            mPrinter.printMsg(a);
            mInputer.inputMsg(a);
            mPrinter.printMsg(b);
            mInputer.inputMsg(b);
            mPrinter.printMsg(result);
            mInputer.inputMsg(result);
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

    private Inputer mInputer = null;
    public void addInputer(Inputer inputer) {
        mInputer = inputer;
    }
}
