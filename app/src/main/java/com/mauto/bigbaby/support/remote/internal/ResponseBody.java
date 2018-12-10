package com.mauto.bigbaby.support.remote.internal;

import com.mauto.bigbaby.support.remote.model.GankBean;

import java.util.List;

/**
 * Created by haohuidong on 18-12-10.
 */

public class ResponseBody {
    public boolean error;
    public List<GankBean> results;

    public String toString() {
        return "\nerror:" + error
                + " \nresults:" + (results == null ? null : results.size());
    }
}
