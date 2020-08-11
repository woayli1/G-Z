package com.gc.iphonemaxplan.Tools;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampManager {

    private String TAG = "TimeStampManager";

    private TimeStampManager() {

    }

    public static TimeStampManager getInstance() {
        return TimeStampManagerHolder.timeStamp;
    }

    private static class TimeStampManagerHolder {
        private static final TimeStampManager timeStamp = new TimeStampManager();
    }

    /* //日期转换为时间戳 */
    public String shortTimeToStamp(String timers) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            return String.valueOf(sf.parse(timers.trim()).getTime());// 日期转换为时间戳
        } catch (ParseException e) {
            Log.e(TAG, "日期转时间戳 err :" + e);
            return null;
        }
    }

    /* //准确时间转换为时间戳 */
    public String timeToStamp(String timers) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return String.valueOf(sf.parse(timers.trim()).getTime());// 日期转换为时间戳
        } catch (ParseException e) {
            Log.e(TAG, "准确时间转时间戳 err :" + e);
            return null;
        }
    }

    /* //时间戳转换准确时间 */
    public String stampToTime(String stamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.parseLong(stamp))); // 时间戳转换日期
    }
}
