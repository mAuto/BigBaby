package com.mauto.bigbaby.support.remote;


import com.mauto.bigbaby.support.remote.internal.ResponseBody;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.mauto.bigbaby.support.remote.NetPointer.API_CATEGORY_ANDROID;
import static com.mauto.bigbaby.support.remote.NetPointer.API_RANDOM_PATH;
import static com.mauto.bigbaby.support.remote.NetPointer.SEPARATOR;

/**
 * Created by haohuidong on 18-12-10.
 */

public interface ApiPointer {

    @GET(API_RANDOM_PATH + API_CATEGORY_ANDROID + SEPARATOR + "{count}")
    Observable<ResponseBody> fetchAndroidRandomData(@Path("count") int count);

}
