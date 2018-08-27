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

        return new StringBuffer(a).append(b).toString();
    }

    public String fun_0 (){
        return null;
    }

    public String fun_1 (){
        return null;
    }

    public String fun_2 (){
        return null;
    }
}
