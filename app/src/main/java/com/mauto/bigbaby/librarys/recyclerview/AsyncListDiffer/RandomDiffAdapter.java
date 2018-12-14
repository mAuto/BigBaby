package com.mauto.bigbaby.librarys.recyclerview.AsyncListDiffer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.DiffUtil;
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

public class RandomDiffAdapter extends RecyclerView.Adapter<RandomDiffAdapter.ViewHolder> {

    private Context mContext;
    public RandomDiffAdapter(Context context) {
        mContext = context;

        mDiffer = new AsyncListDiffer<GankBean>(this, callback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.libs_recycler_item_layout, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        Log.e(">>>>>>", "RandomDiffAdapter --> " + "onBindViewHolder_payload"+" position:"+position);
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
        Log.e(">>>>>>", "RandomDiffAdapter --> " + "onBindViewHolder"+" position:"+position);
        if (holder != null) {
            GankBean bean = mDiffer.getCurrentList().get(position);

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
        return mDiffer.getCurrentList().size();
    }

    public void fillData(List<GankBean> data) {
        mDiffer.submitList(data);
    }

    public List<GankBean> getOriginalData() {
        return mDiffer.getCurrentList();
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

    /////////////////////////////////////////--> 18-12-14 下午5:45 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> async differ <-- ↓↓↓/////////////////////////////////////
    private AsyncListDiffer<GankBean> mDiffer;

    private DiffUtil.ItemCallback<GankBean> callback = new DiffUtil.ItemCallback<GankBean>() {
        @Override
        public boolean areItemsTheSame(GankBean oldItem, GankBean newItem) {
            return TextUtils.equals(oldItem._id, newItem._id);
        }

        @Override
        public boolean areContentsTheSame(GankBean oldItem, GankBean newItem) {
            return oldItem.isEqualsTo(newItem);
        }

        @Nullable
        @Override
        public Object getChangePayload(GankBean oldBean, GankBean newBean) {

            Bundle payload = null;


            if (!TextUtils.equals(oldBean.desc, newBean.desc)) {
                if (payload == null)
                    payload  = new Bundle();
                payload.putString("desc", newBean.desc);
            }
            if (!TextUtils.equals(oldBean.type, newBean.type)) {
                if (payload == null)
                    payload  = new Bundle();
                payload.putString("type", newBean.type);
            }

            if (!TextUtils.equals(oldBean.createAt, newBean.createAt)
                    || !TextUtils.equals(oldBean.publishedAt, newBean.publishedAt)
                    || !TextUtils.equals(oldBean.source, newBean.source)
                    || !TextUtils.equals(oldBean.url, newBean.url)
                    || !TextUtils.equals(oldBean.who, newBean.who)
                    || !TextUtils.equals(oldBean.used, newBean.used))
                if (payload == null)
                    payload  = new Bundle();

            return payload;
        }
    };
    /////////////////////////////////////↑↑↑ --> async differ <-- ↑↑↑/////////////////////////////////////
}
