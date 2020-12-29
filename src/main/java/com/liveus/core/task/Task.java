package com.liveus.core.task;

import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Package: com.liveus.core.task
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/4 16:41
 */
@Component
public class Task {

    @Scheduled(cron = "${jobs.schedule}")
    public void test(){
        System.out.println(Calendar.getInstance().getTime()+"task");
    }

    public static final String PATTERN_I = "yyyy-MM-dd HH:mm:ss";

    public static Date lastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int index = calendar.get(Calendar.DAY_OF_WEEK);
//        修正index偏移量，外国 星期天为第一天，我们周一为第一天，所以需要-1
        index = ((index - 1) == 0 ? 7 : (index - 1));

        calendar.add(Calendar.DAY_OF_YEAR, -1 * index);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String dateStr = year + "-" + month + "-" + day + " 23:59:59";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_I);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date addDay(Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, step);
        return calendar.getTime();
    }

    public static Date addHour(Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, step);
        return calendar.getTime();
    }

    @Test
    public void test1(){
        System.out.println(addHour(Calendar.getInstance().getTime(),62));
    }
}
