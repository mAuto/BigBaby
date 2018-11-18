package com.mauto.bigbaby.librarys.recyclerview.sortedlist;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;

/**
 * Created by haohuidong on 18-11-2.
 */

public class SortedListCallback extends SortedListAdapterCallback<ViolinComposition> {

    public SortedListCallback(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    public int compare(ViolinComposition o1, ViolinComposition o2) {
        return Integer.valueOf(o1.id).compareTo(o2.id);
    }

    @Override
    public boolean areContentsTheSame(ViolinComposition oldItem, ViolinComposition newItem) {
        return oldItem.areContentsTheSame(newItem);
    }

    @Override
    public boolean areItemsTheSame(ViolinComposition item1, ViolinComposition item2) {
        return item1.areCompositionsTheSame(item2);
    }
}
