package com.mauto.bigbaby.librarys.recyclerview;

import com.mauto.bigbaby.librarys.recyclerview.sortedlist.ViolinComposition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohuidong on 18-11-1.
 */

public class BigRecyclerDataBuilder {

    public static List<String> buildStringArray() {
        List<String> tmpStrings = new ArrayList<>();

        tmpStrings.add("Master of Tides");
        tmpStrings.add("Transcendence");
        tmpStrings.add("Senbonzakura");
        tmpStrings.add("Song of the Caged Bird");
        tmpStrings.add("Shatter Me");
        tmpStrings.add("Take Flight");
        tmpStrings.add("Ascendance");
        tmpStrings.add("The Arena");
        tmpStrings.add("V-Pop");
        tmpStrings.add("Heist");
        tmpStrings.add("Electric Daisy Violin");
        tmpStrings.add("Dance Of The Sugar Plum Fairy");
        tmpStrings.add("Waltz (Bonus Track)");
        tmpStrings.add("Prism");
        tmpStrings.add("The Phoenix");
        tmpStrings.add("Powerlines (Bonus Track)");
        tmpStrings.add("Beyond the Veil");
        tmpStrings.add("Les Misérables Medley");
        tmpStrings.add("Carol Of The Bells");

        return tmpStrings;
    }

    public static List<Integer> buildSortedInteger() {
        List<Integer> tmpIntegers = new ArrayList<>();

        tmpIntegers.add(0);
        tmpIntegers.add(1);
        tmpIntegers.add(3);
        tmpIntegers.add(10);
        tmpIntegers.add(13);
        tmpIntegers.add(18);
        tmpIntegers.add(19);
        tmpIntegers.add(21);
        tmpIntegers.add(23);
        tmpIntegers.add(67);
        tmpIntegers.add(89);
        tmpIntegers.add(90);
        tmpIntegers.add(92);
        tmpIntegers.add(97);
        tmpIntegers.add(110);
        tmpIntegers.add(114);
        tmpIntegers.add(119);
        tmpIntegers.add(120);
        tmpIntegers.add(121);
        tmpIntegers.add(128);
        tmpIntegers.add(130);
        tmpIntegers.add(156);
        tmpIntegers.add(179);
        tmpIntegers.add(190);
        tmpIntegers.add(198);
        tmpIntegers.add(199);
        tmpIntegers.add(200);

        return tmpIntegers;
    }

    public static List<ViolinComposition> buildSortedCompositions() {

        List<String> titles = buildStringArray();
        List<Integer> ids = buildSortedInteger();

        List<ViolinComposition> tmpCompositions = new ArrayList<>();
        ViolinComposition tmpComposition;
        for (int i=0;i<titles.size();i++) {
            tmpComposition = new ViolinComposition();
            tmpComposition.id = ids.get(i);
            tmpComposition.title = titles.get(i);
            tmpComposition.artist = "Lindsey Stirling";
            tmpCompositions.add(tmpComposition);
        }

        return tmpCompositions;
    }

}
