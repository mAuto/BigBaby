package com.mauto.bigbaby.support.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haohuidong on 18-9-25.
 */

public class GankBean implements Parcelable {

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
}
