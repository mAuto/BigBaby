package com.mauto.bigbaby.librarys.recyclerview.AsyncListUtil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauto.bigbaby.R;

/**
 * Created by haohuidong on 18-11-19.
 */

public class BigAsyncAdapter extends RecyclerView.Adapter<BigAsyncAdapter.ViewHolder> {

    private BigAsyncListUtil mAsyncListUtil;
    private Context mContext;

    public BigAsyncAdapter(Context context, AsyncListUtil util) {
        mAsyncListUtil = (BigAsyncListUtil) util;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.lib_recycler_base, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = mAsyncListUtil.getItem(position);
        if (TextUtils.isEmpty(title))
            holder.tvTitle.setText("loading...");
        else
            holder.tvTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return mAsyncListUtil.getItemCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_content);
        }
    }
}
