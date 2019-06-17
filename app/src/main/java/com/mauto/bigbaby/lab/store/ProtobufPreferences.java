package com.mauto.bigbaby.lab.store;

import android.content.SharedPreferences;
import android.support.annotation.GuardedBy;
import android.util.Log;

import com.tencent.mmkv.MMKV;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by haohuidong on 19-6-6.
 */

public class ProtobufPreferences {

    //region >>> singleton
    /*
    * 19-6-6 下午3:21
    * */
    /////////////////////////////////////↓↓↓↓↓↓↓↓↓/////////////////////////////////////
    private final static String MIGRATE_RECORD = "migrate_record";
    private ProtobufPreferences(){
        mPreferences = new HashMap<>();
        mPreferences.put(MIGRATE_RECORD, MMKV.mmkvWithID(MIGRATE_RECORD));
    }

    @GuardedBy("mLock")
    private Map<String, SharedPreferences> mPreferences = null;

    private static class InstanceHolder {
        private static ProtobufPreferences sInstance = new ProtobufPreferences();
    }

    public static ProtobufPreferences manage() {
        return InstanceHolder.sInstance;
    }
    /////////////////////////////////////↑↑↑↑↑↑↑↑↑/////////////////////////////////////

    public boolean hasMigratedData(String name) {
        if (mPreferences.get(MIGRATE_RECORD).contains(name))
            return true;
        return false;
    }

    private final Object mLock = new Object();

    public SharedPreferences of(String name, int mode, SharedPreferences preferences) {
        synchronized (mLock) {
            if (mPreferences.containsKey(name))
                return mPreferences.get(name);
            else {
                ProtobufPreferencesImpl localPreferences = new ProtobufPreferencesImpl(name, mode);
                if (preferences != null) {
                    if (preferences.getAll() != null && preferences.getAll().size() > 0) {
                        localPreferences.importFromSharedPreferences(preferences);
                        Log.e(">>> ProtobufPreferences", "of@62 --> " + " " + "data had bean migrated");
                    }
                    mPreferences.get(MIGRATE_RECORD).edit().putString(name, "data had bean migrated");
                }
                mPreferences.put(name, localPreferences);
                return localPreferences;
            }
        }
    }

    public SharedPreferences of(String name, int mode) {
        return of(name, mode, null);
    }

    public SharedPreferences of(String name) {
        return of(name, MODE_PRIVATE);
    }

}
