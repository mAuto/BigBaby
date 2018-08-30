package com.mauto.bigbaby.ut;

/**
 * Created by haohuidong on 18-8-24.
 */

public class LogSys {

    private static String TAG = "--> LogSys <--";

    public static void print(String msg){
        print(TAG, msg);
    }

    public static void appendPrint(String msg){
        System.out.print(msg);
    }

    public static void print(String tag, String msg){
        System.out.println(tag + ":   " + msg);
    }

}
