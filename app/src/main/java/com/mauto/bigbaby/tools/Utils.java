package com.mauto.bigbaby.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by haohuidong on 18-10-22.
 */

public class Utils {
    public static String formatTime(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt = new Date(time);
        String dateTime = sdf.format(dt);
        return dateTime;
    }
}
