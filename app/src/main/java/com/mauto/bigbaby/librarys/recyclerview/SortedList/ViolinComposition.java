package com.mauto.bigbaby.librarys.recyclerview.SortedList;

/**
 * Created by haohuidong on 18-11-2.
 */

public class ViolinComposition {
    public int id;
    public String title;
    public String artist;

    public boolean areCompositionsTheSame(ViolinComposition composition) {
        return id == composition.id;
    }

    public boolean areContentsTheSame(ViolinComposition composition) {
        if (artist.equals(composition.artist) && title.equals(composition))
            return true;
        else return false;
    }
}
