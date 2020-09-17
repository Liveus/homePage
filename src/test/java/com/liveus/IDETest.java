package com.liveus;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Package: com.liveus
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/11 13:56
 */
public class IDETest {

    @Test
    public void test(){

        Date now = new Date();
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH,18);
        startTime.set(Calendar.HOUR_OF_DAY,0);
        startTime.set(Calendar.MINUTE,0);
        startTime.set(Calendar.SECOND,0);
        System.out.println(now);
        System.out.println(startTime.getTime());
        System.out.println((startTime.getTime().getTime()-now.getTime())/(60*1000));

//        System.out.println("1");
//        for (int i = 0; i < 100; i++) {
//            System.out.println(i);
//        }
        User user1 = new User();
        Calendar ca1 = Calendar.getInstance();
        ca1.add(Calendar.YEAR,1);
        user1.setId(1);
        user1.setTime(ca1.getTime());
        User user2 = new User();
        Calendar ca2 = Calendar.getInstance();
        ca2.add(Calendar.YEAR,2);
        user2.setId(2);
        user2.setTime(ca2.getTime());
        User user3 = new User();
        Calendar ca3 = Calendar.getInstance();
        ca3.add(Calendar.YEAR,3);
        user3.setId(3);
        user3.setTime(ca3.getTime());
        List<User> userList = new ArrayList<>(3);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        List<Integer> ids = userList.stream().sorted(Comparator.comparing(User::getTime)).map(User::getId).collect(Collectors.toList());
        for (Integer id:ids) {
            System.out.println(id);
        }
    }

    class User{
        Integer id;
        Date time;

        public void setId(Integer id) {
            this.id = id;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public Date getTime() {
            return time;
        }

        public Integer getId() {
            return id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", time=" + time +
                    '}';
        }
    }
}
