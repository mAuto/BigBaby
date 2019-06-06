package com.mauto.bigbaby.lab.store;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Created by haohuidong on 19-6-6.
 */

public class LocalPreferencesImpl implements SharedPreferences, SharedPreferences.Editor {


    //region >>> 真正的存储核心 - MMKV
    /*
    * 19-6-6 上午11:54
    * */
    /////////////////////////////////////↓↓↓↓↓↓↓↓↓/////////////////////////////////////
    private final Object mLock = new Object();

    private String[] mTypeArray = new String[]{
            "b@", "i@", "l@", "f@", "s@", "ss@"
    };

    private MMKV mStoreCore = null;
    @GuardedBy("mLock")
    private Map<String, Object> mMap;
    @GuardedBy("mLock")
    private List<String> mModified;
    @GuardedBy("mLock")
    private boolean mLoaded = false;

    private void startLoadFromDisk() {
        synchronized (mLock) {
            mLoaded = false;
        }
        new Thread("SharedPreferencesImpl-load") {
            public void run() {
                loadFromDisk();
            }
        }.start();
    }

    private void loadFromDisk() {
        synchronized (mLock) {
            if (mLoaded) {
                return;
            }
        }

        String[] allKeys = mStoreCore.allKeys();
        Map map = null;
        if (allKeys != null) {
            map = new HashMap();
            for (String key : allKeys) {
                String[] keyArray = key.split("@");
                if (keyArray == null || keyArray.length < 2)
                    map.put(key, null);
                else {
                    switch (keyArray[0]) {
                        case "b":{
                            map.put(keyArray[1], mStoreCore.getBoolean(key, false));
                        }break;
                        case "i":{
                            map.put(keyArray[1], mStoreCore.getInt(key, 0));
                        }break;
                        case "l":{
                            map.put(keyArray[1], mStoreCore.getLong(key, 0));
                        }break;
                        case "f":{
                            map.put(keyArray[1], mStoreCore.getFloat(key, 0));
                        }break;
                        case "s":{
                            map.put(keyArray[1], mStoreCore.getString(key, ""));
                        }break;
                        case "ss":{
                            map.put(keyArray[1], mStoreCore.getStringSet(key, null));
                        }break;
                        default:{
                            map.put(keyArray[1], null);
                        }
                    }
                }
            }
        }

        synchronized (mLock) {
            mLoaded = true;
            if (map != null) {
                mMap = map;
            }
            Log.e("LocalPreferencesImpl", "loadFromDisk@108 --> " + " " + mMap.size());
            mLock.notifyAll();
        }

    }
    /////////////////////////////////////↑↑↑↑↑↑↑↑↑/////////////////////////////////////

    public LocalPreferencesImpl(String name, int mode){
        mStoreCore = MMKV.mmkvWithID(name, mode);
        mMap = new HashMap<>();
        startLoadFromDisk();
    }

    @Override
    public Map<String, ?> getAll() {
        return mMap;
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        return mStoreCore.getString("s@"+key, defValue);
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return mStoreCore.getStringSet("ss@"+key, defValues);
    }

    @Override
    public int getInt(String key, int defValue) {
        return mStoreCore.getInt("i@"+key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return mStoreCore.getLong("l@"+key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return mStoreCore.getFloat("f@"+key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return mStoreCore.getBoolean("b@"+key, defValue);
    }

    @Override
    public boolean contains(String key) {
        if (mMap == null) {
            for (String type : mTypeArray) {
                if (mStoreCore.contains(type + key))
                    return true;
            }
            return false;
        } else
            return mMap.containsKey(key);
    }

    @Override
    public Editor edit() {
        if (mModified == null)
            mModified = new ArrayList<>();
        else
            mModified.clear();
        return this;
    }

    //region >>> 重新实现监听时间
    /*
    * 19-6-6 下午2:34
    * */
    /////////////////////////////////////↓↓↓↓↓↓↓↓↓/////////////////////////////////////
    private static final Object CONTENT = new Object();
    @GuardedBy("mLock")
    private final WeakHashMap<OnSharedPreferenceChangeListener, Object> mListeners =
            new WeakHashMap<OnSharedPreferenceChangeListener, Object>();

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        synchronized(mLock) {
            mListeners.put(listener, CONTENT);
        }
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        synchronized(mLock) {
            mListeners.remove(listener);
        }
    }

    private static class MemoryCommitResult {
        @Nullable final List<String> keysModified;
        @Nullable final Set<OnSharedPreferenceChangeListener> listeners;

        private MemoryCommitResult(@Nullable List<String> keysModified,
                                   @Nullable Set<OnSharedPreferenceChangeListener> listeners) {
            this.keysModified = keysModified;
            this.listeners = listeners;
        }

    }

    private void notifyListeners(final MemoryCommitResult mcr) {
        if (mcr.listeners == null || mcr.keysModified == null ||
                mcr.keysModified.size() == 0) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            for (int i = mcr.keysModified.size() - 1; i >= 0; i--) {
                final String key = mcr.keysModified.get(i);
                for (OnSharedPreferenceChangeListener listener : mcr.listeners) {
                    if (listener != null) {
                        listener.onSharedPreferenceChanged(this, key);
                    }
                }
            }
        } else {
            // Run this function on the main thread.
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    notifyListeners(mcr);
                }
            });
        }
    }
    /////////////////////////////////////↑↑↑↑↑↑↑↑↑/////////////////////////////////////

    @Override
    public Editor putString(String key, @Nullable String value) {
        synchronized (mLock) {
            if (mModified != null && !mModified.contains(key)) {
                mModified.add(key);
            }
            mMap.put(key, value);
        }
        mStoreCore.putString("s@"+key, value);
        return this;
    }

    @Override
    public Editor putStringSet(String key, @Nullable Set<String> values) {
        synchronized (mLock) {
            if (mModified != null && !mModified.contains(key))
                mModified.add(key);
            mMap.put(key, values);
        }
        mStoreCore.putStringSet("ss@"+key, values);
        return this;
    }

    @Override
    public Editor putInt(String key, int value) {
        synchronized (mLock) {
            if (mModified != null && !mModified.contains(key))
                mModified.add(key);
            mMap.put(key, value);
        }
        mStoreCore.putInt("i@"+key, value);
        return this;
    }

    @Override
    public Editor putLong(String key, long value) {
        synchronized (mLock) {
            if (mModified != null && !mModified.contains(key))
                mModified.add(key);
            mMap.put(key, value);
        }
        mStoreCore.putLong("l@"+key, value);
        return this;
    }

    @Override
    public Editor putFloat(String key, float value) {
        synchronized (mLock) {
            if (mModified != null && !mModified.contains(key))
                mModified.add(key);
            mMap.put(key, value);
        }
        mStoreCore.putFloat("f@"+key, value);
        return this;
    }

    @Override
    public Editor putBoolean(String key, boolean value) {
        synchronized (mLock) {
            if (mModified != null && !mModified.contains(key))
                mModified.add(key);
            mMap.put(key, value);
        }
        mStoreCore.putBoolean("b@"+key, value);
        return this;
    }

    @Override
    public Editor remove(String key) {
        synchronized (mLock) {
            if (mModified != null && !mModified.contains(key))
                mModified.add(key);
            mMap.remove(key);
        }
        String[] allKeys = mStoreCore.allKeys();
        for (String tmpKey : allKeys) {
            if (tmpKey.contains(key))
                return mStoreCore.remove(tmpKey);
        }
        mStoreCore.remove(key);
        return this;
    }

    @Override
    public Editor clear() {
        synchronized (mLock) {
            if (mModified != null)
                mModified.clear();
            mMap.clear();
            mListeners.clear();
        }
        mStoreCore.clear();
        return this;
    }

    @Override
    public boolean commit() {
        notifyListeners(new MemoryCommitResult(mModified, new HashSet<>(mListeners.keySet())));
        return true;
    }

    @Override
    public void apply() {
        notifyListeners(new MemoryCommitResult(mModified, new HashSet<>(mListeners.keySet())));
    }

    //region >>> 老数据迁移
    /*
    * 19-6-6 上午11:36
    * */
    /////////////////////////////////////↓↓↓↓↓↓↓↓↓/////////////////////////////////////
    public int importFromSharedPreferences(SharedPreferences preferences) {
        Map<String, ?> kvs = preferences.getAll();
        if (kvs == null || kvs.size() <= 0) {
            return 0;
        }

        for (Map.Entry<String, ?> entry : kvs.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key == null || value == null) {
                continue;
            }

            if (value instanceof Boolean) {
                putBoolean(key, (boolean) value);
            } else if (value instanceof Integer) {
                putInt(key, (int) value);
            } else if (value instanceof Long) {
                putLong(key, (long) value);
            } else if (value instanceof Float) {
                putFloat(key, (float) value);
            } else if (value instanceof String) {
                putString(key, (String) value);
            } else if (value instanceof Set) {
                putStringSet(key, (Set<String>) value);
            } else {
                Log.e("LocalPreferencesImpl", "unknown type: " + value.getClass());
            }
        }
        return kvs.size();
    }
    /////////////////////////////////////↑↑↑↑↑↑↑↑↑/////////////////////////////////////

}
