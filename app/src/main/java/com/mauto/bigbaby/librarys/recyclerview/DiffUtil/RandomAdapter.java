package com.mauto.bigbaby.librarys.recyclerview.DiffUtil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.ViewHolder> {

    private Context mContext;
    public RandomAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.libs_recycler_item_layout, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("--> RandomAdapter <--", "onBindViewHolder"+" position:"+position);
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

    private List<GankBean> mData;
    public void fillData(List<GankBean> data) {
        if (data == null || data.size() == 0)
            return;

        if (mData == null)
            mData = new ArrayList<>();

        mData.clear();
        mData.addAll(data);
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

    public List<GankBean> getOriginalData() {
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
