package com.mauto.bigbaby.support.remote.internal;

import com.google.gson.GsonBuilder;
import com.mauto.bigbaby.support.remote.ApiPointer;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mauto.bigbaby.support.remote.NetPointer.BASE_API_LINK;

/**
 * Created by haohuidong on 18-12-10.
 */

public class NetHunter {

    /////////////////////////////////////////--> 18-12-10 下午6:59 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> singleton <-- ↓↓↓/////////////////////////////////////
    private NetHunter() {
        initNetSupport();
    }
    private static class InstanceHolder {
        private static NetHunter mInstance = new NetHunter();
    }

    public static NetHunter getInstance() {
        return InstanceHolder.mInstance;
    }
    /////////////////////////////////////↑↑↑ --> singleton <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-12-10 下午7:03 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> init net support <-- ↓↓↓/////////////////////////////////////
    private ApiPointer mPointer;
    private void initNetSupport() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                                .connectTimeout(20, TimeUnit.SECONDS)
                                .build())
                .baseUrl(BASE_API_LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mPointer = retrofit.create(ApiPointer.class);
    }

    public ApiPointer getApiPointer() {
        return mPointer;
    }
    /////////////////////////////////////↑↑↑ --> init net support <-- ↑↑↑/////////////////////////////////////

}
