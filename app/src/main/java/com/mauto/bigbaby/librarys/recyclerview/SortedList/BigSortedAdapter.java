package com.mauto.bigbaby.librarys.recyclerview.SortedList;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauto.bigbaby.R;

import java.util.List;

/**
 * Created by haohuidong on 18-11-1.
 */

public class BigSortedAdapter extends RecyclerView.Adapter<BigSortedAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_recycler_base, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder != null) {
            holder.tvContent.setText(mCompositions.get(position).title);
        }
    }

    @Override
    public int getItemCount() {
        return mCompositions.size();
    }

    SortedList<ViolinComposition> mCompositions;
    public void setData(List<ViolinComposition> data) {
        if (data == null)
            return;
        mCompositions.beginBatchedUpdates();
        mCompositions.addAll(data);
        mCompositions.endBatchedUpdates();
    }

    public void insertData(int value) {

    }

    public void bindSortedList(SortedList<ViolinComposition> sortedList) {
        mCompositions = sortedList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
