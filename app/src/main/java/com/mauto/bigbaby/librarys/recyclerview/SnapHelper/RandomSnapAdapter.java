package com.mauto.bigbaby.librarys.recyclerview.SnapHelper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mauto.bigbaby.R;
import com.mauto.bigbaby.support.remote.model.GankBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohuidong on 18-9-26.
 */

public class RandomSnapAdapter extends RecyclerView.Adapter<RandomSnapAdapter.ViewHolder> {

    private Context mContext;
    private int mOrientation = RecyclerView.VERTICAL;
    public RandomSnapAdapter(Context context, @RecyclerView.Orientation int orientation) {
        mContext = context;
        mOrientation = orientation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.libs_recycler_item_layout;

        if (mOrientation == RecyclerView.HORIZONTAL)
            layoutId = R.layout.libs_recycler_horizontal_item_layout;

        return new ViewHolder(LayoutInflater.from(mContext).inflate(layoutId, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.e(">>>>>>", "RandomSnapAdapter --> " + "onBindViewHolder_payload"+" position:"+position);
        Bundle payload = null;
        if (payloads != null && payloads.size() > 0)
            payload = (Bundle) payloads.get(0);

        if (payload == null)
            super.onBindViewHolder(holder, position, payloads);
        else {
            if (holder != null) {
                String type = payload.getString("type");
                if (!TextUtils.isEmpty(type))
                    holder.tvTitle.setText(type);

                String desc = payload.getString("desc");
                if (!TextUtils.isEmpty(desc))
                    holder.tvDesc.setText(desc);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e(">>>>>>", "RandomSnapAdapter --> " + "onBindViewHolder"+" position:"+position);
        if (holder != null) {
            GankBean bean = mData.get(position);

            holder.tvTitle.setText(bean.type);
            holder.tvDesc.setText(bean.desc);

            if (bean.images != null && bean.images.length > 0){
                Glide.with(mContext)
                        .load(bean.images[0])
                        .into(holder.ivCover);
            } else {
                holder.ivCover.setImageResource(R.mipmap.ic_launcher_round);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            mData = new ArrayList<>();
        return mData.size();
    }

    private ArrayList<GankBean> mData;
    public void fillData(List<GankBean> data) {
        if (data == null || data.size() == 0)
            return;

        if (mData == null)
            mData = new ArrayList<>();

        int size = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(size, data.size());
    }

    public void appendData(List<GankBean> data) {
        if (data == null || data.size() == 0)
            return;

        if (mData == null)
            mData = new ArrayList<>();

        int size = data.size();
        mData.addAll(data);
        notifyItemRangeInserted(mData.size(), size);
    }

    public ArrayList<GankBean> getOriginalData() {
        if (mData == null)
            mData = new ArrayList<>();
        return mData;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivCover;
        private TextView tvTitle, tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc =  itemView.findViewById(R.id.tv_desc);
        }
    }
}
