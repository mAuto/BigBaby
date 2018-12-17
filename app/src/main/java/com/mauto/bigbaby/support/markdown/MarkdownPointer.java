package com.mauto.bigbaby.support.markdown;

/**
 * Created by haohuidong on 18-11-19.
 */

public class MarkdownPointer {

    /////////////////////////////////////////--> 18-11-19 下午12:24 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> uri options <-- ↓↓↓/////////////////////////////////////
    /*
    * for uri configuration
    * */
    public final static String MD_SCHEME = "bigbaby";
    public final static String MD_HOST = "markdown";
    public final static String MD_KEY_LINK = "md_link";
    public final static String MD_KEY_BACK_STACK_ENABLE = "md_back_stack";
    /////////////////////////////////////↑↑↑ --> uri options <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-11-19 下午12:27 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> for libraries <-- ↓↓↓/////////////////////////////////////
    /*
    * link of markdown file about libraries
    * */
    public final static String MD_LINK_LIB_RECYCLER_ASYNCLISTUTIL = "https://github.com/mAuto/BigBaby/blob/master/app/src/main/java/com/mauto/bigbaby/librarys/recyclerview/AsyncListUtil/AsyncListUtil.md";
    public final static String MD_LINK_LIB_RECYCLER_DIFFUTIL = "https://github.com/mAuto/BigBaby/blob/master/app/src/main/java/com/mauto/bigbaby/librarys/recyclerview/DiffUtil/DiffUtil.md";
    public final static String MD_LINK_LIB_RECYCLER_ASYNCLISTDIFFER = "https://github.com/mAuto/BigBaby/blob/master/app/src/main/java/com/mauto/bigbaby/librarys/recyclerview/AsyncListDiffer/AsyncListDiffer.md";
    public final static String MD_LINK_LIB_RECYCLER_SNAP = "https://github.com/mAuto/BigBaby/blob/master/app/src/main/java/com/mauto/bigbaby/librarys/recyclerview/SnapHelper/SnapHelper.md";
    public final static String MD_LINK_LIB_RECYCLER_SORTEDLIST = "";
    /////////////////////////////////////↑↑↑ --> for libraries <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-11-19 下午3:53 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> for err <-- ↓↓↓/////////////////////////////////////
    /*
    * link of markdown file about err
    * */
    public final static String MD_LINK_ERR_FRAGMENT_TAG = "https://github.com/mAuto/BigBaby/blob/master/app/src/main/java/com/mauto/bigbaby/err/fragment/tag/Insert_Fragments_to_ViewPager_dynamically.md";
    /////////////////////////////////////↑↑↑ --> for err <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-11-19 下午2:41 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> build uri <-- ↓↓↓/////////////////////////////////////
    /*
    * return uri which contains designated scheme and link about markdown file
    * */
    public static String buildUri(String link) {
        StringBuilder builder = new StringBuilder();
        builder.append(MD_SCHEME).append("://").append(MD_HOST);
        builder.append("?").append(MD_KEY_LINK).append("=").append(link);
        builder.append("&").append(MD_KEY_BACK_STACK_ENABLE).append("=").append(false);
        return builder.toString();
    }
    /////////////////////////////////////↑↑↑ --> build uri <-- ↑↑↑/////////////////////////////////////

}
