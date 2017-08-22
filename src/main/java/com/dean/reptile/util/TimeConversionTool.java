package com.dean.reptile.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 2017/8/16.
 */
public class TimeConversionTool {

    public static String unixTimeToString (long unix) {
        Date date = new Date(unix);
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return time.format(date);
    }
}
