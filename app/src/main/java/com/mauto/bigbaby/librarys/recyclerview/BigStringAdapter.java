package com.mauto.bigbaby.librarys.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauto.bigbaby.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haohuidong on 18-11-1.
 */

public class BigStringAdapter extends RecyclerView.Adapter<BigStringAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_recycler_base, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder != null) {
            holder.tvContent.setText(mContents.get(position));
        }
    }

    @Override
    public int getItemCount() {
        initDataArrayIfNecessary();
        return mContents.size();
    }

    private List<String> mContents;
    public void setData(List<String> data) {
        if (data == null)
            return;

        initDataArrayIfNecessary();

        int size  = mContents.size();
        mContents.clear();
        notifyItemRangeRemoved(0, size);
        mContents.addAll(data);
        size = mContents.size();
        notifyItemRangeChanged(0, size);
    }

    private void initDataArrayIfNecessary() {
        if (mContents == null)
            mContents = new ArrayList<>();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
