package com.dean.reptile.constant;

import java.util.HashMap;
import java.util.Map;

public class TimesCache {

    private static TimesCache timesCache = new TimesCache();
    private TimesCache() {
    }

    private static Map<Integer, Long> timeMap = new HashMap<>();

    static {

    }

    public static TimesCache getInstance() {
        return timesCache;
    }


    public static Long getTimeByIndex(Integer index) {
        return timeMap.get(index);
    }

    public static Long getTime(JobEnum job) {
        return getTimeByIndex(JobEnum.getIndex(job));
    }

    public static boolean updateTime(JobEnum job, Long timestamp) {
        Integer index = JobEnum.getIndex(job);
        timeMap.put(index, timestamp);
        return timeMap.get(JobEnum.getIndex(job)).equals(timestamp);
    }
}
