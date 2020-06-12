package com.se1722.englishassistant.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class BeijingTime {
    /**
     * 返回东八区的时间
     * @return
     */
    public static String getChinaTime() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date());
    }
}
