package com.mauto.bigbaby.err.firebase_json;

import com.google.gson.Gson;
import com.mauto.bigbaby.ut.LogSys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by haohuidong on 18-9-6.
 */

public class BigErrJsonBuilder {

    private String json = "{title:homesick, singer:Dua lipa, ablum:Homesick}";

    public String parse(String key) {
        LogSys.print(key);
        String value = null;
        try {
            JSONObject parent = new JSONObject(json);
            value = parent.getString(key);
        } catch (JSONException e) {
            value = e.toString();
        }
        return value;
    }

//    public String parseByGson(String key) {
//        String value = null;
//        try {
//            Gson gson = new Gson(json);
//            value = gson.getString(key);
//        } catch (JSONException e) {
//            value = null;
//        }
//        return value;
//    }

}
