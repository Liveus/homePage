package com.liveus.homePage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class Test {

    public static void main(String[] args) {
        var a = "aa";
        System.out.println(a.getClass());

/*        List list = List.of("Java","Python","C");
        List copy = List.copyOf(list);
        System.out.println(list==copy);*/

        var list = new ArrayList<String>();
        var copy = List.copyOf(list);
        System.out.println(list == copy);   // false 


    }
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, -1);//增加一天
            //cd.add(Calendar.MONTH,n);//增加一个月
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            return null;
        }
    }
}
