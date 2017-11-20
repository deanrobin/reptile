package com.dean.reptile.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 2017/8/16.
 */
public class TimeTool {

    public static String unixTimeToString (long unix) {
        Date date = new Date(unix);
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return time.format(date);
    }

    public static long StringToUnixTime(String str, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            Date date = new SimpleDateFormat(format).parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

}
