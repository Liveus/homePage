package com.liveus.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransfrom {

    /**
     * string转date
     * @param timestamp
     * @return
     */
    public static Date StringToDate(String timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date转string
     * @param date
     * @return
     */
    public static String DateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(date);
        return timestamp;
    }

    /**
     * 取时间差,毫秒为结果
     * @param date1
     * @param date2
     * @return
     */
    public static long seconds(Date date1,Date date2){
        return Math.abs(date1.getTime()-date2.getTime());
    }
}
