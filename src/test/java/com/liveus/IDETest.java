package com.liveus;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.sql.SQLOutput;
import java.time.LocalTime;
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
    public static String handleScoreContent(String scoreContent) {
        if (Objects.isNull(scoreContent)) {
            return "";
        }
        List<ScoreContent> list =  JSON.parseArray(scoreContent,ScoreContent.class);
        StringBuilder stringBuilder = new StringBuilder();
        for (ScoreContent score:list) {
            if(StringUtils.isNotEmpty(score.getContent())){
                stringBuilder.append(score.getContent()).append(";");
            }
        }
        return stringBuilder.length()>0?stringBuilder.substring(0, stringBuilder.length() - 1):stringBuilder.toString();
    }


    @Data
    static class ScoreContent{
        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("评分内容")
        private String content;
    }

    public static void main(String[] args) {
        //System.out.println(handleScoreContent("[{\"id\":\"53177f43bd354aa0b78c02ecb6817c55\",\"content\":\"影响因素2\"}]"));
        //System.out.println(handleScoreContent("[{\"id\":\"d397871749be4b9197dd69e48af1cec0\",\"content\":\"\"},{\"content\":\"\"}]"));
        LocalTime localTime = LocalTime.now();

        String[] strs =  "11:20".split(":");

        LocalTime targetTime = LocalTime.of(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]));
        System.out.println(targetTime.toSecondOfDay()-localTime.toSecondOfDay());
        System.out.println(targetTime);
    }
}
