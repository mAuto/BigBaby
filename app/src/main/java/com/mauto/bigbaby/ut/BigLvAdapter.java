package com.mauto.bigbaby.ut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mauto.bigbaby.R;

/**
 * Created by haohuidong on 18-9-4.
 */

public class BigLvAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mData;

    public BigLvAdapter (Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mData == null)
            mData = new String[]{};
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sample, parent, false);
            holder = new ViewHolder();
            holder.tvContent = convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mData != null){
            holder.tvContent.setText(mData[position]);
        }

        return convertView;
    }

    public void setData(String[] data) {
        if (data == null || data.length == 0)
            return;
        mData = data.clone();
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView tvContent;
    }
}
