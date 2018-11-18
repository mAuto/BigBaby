package com.mauto.bigbaby.ut;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauto.bigbaby.R;

/**
 * Created by haohuidong on 18-9-4.
 */

public class BigRvAdapter extends RecyclerView.Adapter<BigRvAdapter.ViewHolder> {

    private Context mContext;

    public BigRvAdapter (Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_sample, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (holder != null){
            holder.tvItem.setText(mData[position]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            mData = new String[]{};
        return mData.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }

    private String[] mData;
    public void setData(String[] data) {
        if (data == null || data.length == 0)
            return;

        mData = data.clone();
        notifyDataSetChanged();
    }

    /////////////////////////////////////////--> 18-9-4 下午4:57 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> item click listener <-- ↓↓↓/////////////////////////////////////
    interface OnItemClickListener {
        void onItemClick(int pos);
    }

    private  OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    /////////////////////////////////////↑↑↑ --> item click listener <-- ↑↑↑/////////////////////////////////////
}
