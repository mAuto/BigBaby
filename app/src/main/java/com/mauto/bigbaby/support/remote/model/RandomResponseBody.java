package com.mauto.bigbaby.support.remote.model;

import com.mauto.bigbaby.support.remote.model.GankBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohuidong on 18-12-10.
 */

public class RandomResponseBody {
    public boolean error;
    public ArrayList<GankBean> results;

    public String toString() {
        return "\nerror:" + error
                + " \nresultBody:" + (results == null ? null : results.size());
    }
}
