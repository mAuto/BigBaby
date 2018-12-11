package com.mauto.bigbaby.support.remote.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by haohuidong on 18-9-25.
 */

public class GankBean implements Parcelable {

    public String _id;

    public String createAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public String used;
    public String who;
    public String[] images;

    protected GankBean(Parcel in) {
        _id = in.readString();
        createAt = in.readString();
        desc = in.readString();
        publishedAt = in.readString();
        source = in.readString();
        type = in.readString();
        url = in.readString();
        used = in.readString();
        who = in.readString();
        images = in.createStringArray();
    }

    public static final Creator<GankBean> CREATOR = new Creator<GankBean>() {
        @Override
        public GankBean createFromParcel(Parcel in) {
            return new GankBean(in);
        }

        @Override
        public GankBean[] newArray(int size) {
            return new GankBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(createAt);
        dest.writeString(desc);
        dest.writeString(publishedAt);
        dest.writeString(source);
        dest.writeString(type);
        dest.writeString(url);
        dest.writeString(used);
        dest.writeString(who);
        dest.writeStringArray(images);
    }

    public boolean isEqualsTo(GankBean bean) {
        if (bean == null)
            return false;

        if (this == bean)
            return true;

        if (!TextUtils.equals(createAt, bean.createAt))
            return false;
        if (!TextUtils.equals(desc, bean.desc))
            return false;
        if (!TextUtils.equals(publishedAt, bean.publishedAt))
            return false;
        if (!TextUtils.equals(source, bean.source))
            return false;
        if (!TextUtils.equals(type, bean.type))
            return false;
        if (!TextUtils.equals(url, bean.url))
            return false;
        if (!TextUtils.equals(who, bean.who))
            return false;
        if (!TextUtils.equals(used, bean.used))
            return false;

        return true;
    }
}
