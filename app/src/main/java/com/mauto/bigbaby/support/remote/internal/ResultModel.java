package com.mauto.bigbaby.support.remote.internal;

import android.text.TextUtils;

import com.google.gson.Gson;


/**
 * Created by haohuidong on 18-9-25.
 */

public class ResultModel extends ResponseModel {
    public ResultModel(String results) {
        state = ModelSate.SUCCESS;
        this.results = results;
    }

    public <T extends BaseModel> T parse(Class<T> clazz) {
        if (TextUtils.isEmpty(results))
            return null;
        return new Gson().fromJson(results, clazz);
    }
}
