package com.liveus;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Desc: 判断是否是周末或者节假日
 * @author: Lenovo
 * @Time: 2019/10/24 16:08

 */
public class HolidayJudge {

    public static void main(String[] args) {
        Boolean[] resultByApi = JudgeByLocal();
        for (Boolean boo : resultByApi) {
            System.out.println(boo);
        }
    }

    // 法律规定的放假日期
    private List<String> lawHolidays   = Arrays.asList(
            // 元旦
            "2018-12-30", "2018-12-31", "2019-01-01",
            // 春节
            "2019-02-04", "2019-02-05", "2019-02-06", "2019-02-07", "2019-02-08", "2019-02-09", "2019-02-10",
            // 清明
            "2019-04-05", "2019-04-06", "2019-04-07",
            // 劳动节
            "2019-05-01", "2019-05-02", "2019-05-03", "2019-05-04",
            // 端午
            "2019-06-7", "2019-06-8", "2019-06-9",
            // 中秋
            "2019-09-13", "2019-09-14", "2019-09-15",
            // 国庆
            "2019-10-01", "2019-10-02", "2019-10-03", "2019-10-04", "2019-10-05",
            "2019-10-06", "2019-10-07");
    // 由于放假需要额外工作的周末
    private List<String> extraWorkdays = Arrays.asList(
            // 元旦
            "2018-12-29",
            // 春节
            "2019-02-02", "2019-02-03",
            // 清明

            // 劳动
            "2019-04-28", "2019-05-05",
            // 端午

            // 中秋

            // 国庆
            "2019-09-29", "2019-10-12");

    /**
     * @author  qyw
     * @description  判断是否是法定假日
     * @date Created in 21:03 2019/1/31
     **/
    public boolean isLawHoliday(String calendar) {
        HolidayJudge.isValidDate(calendar);
        if (lawHolidays.contains(calendar)) {
            return true;
        }
        return false;
    }

    /**
     * @author  qyw
     * @description  判断是否是周末
     * @date Created in 21:03 2019/1/31
     **/
    public boolean isWeekends(String calendar) {
        HolidayJudge.isValidDate(calendar);
        // 先将字符串类型的日期转换为Calendar类型
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(calendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca   = Calendar.getInstance();
        ca.setTime(date);
        if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    /**
     * @author  qyw
     * @description  判断是否是需要额外补班的周末
     * @date Created in 21:06 2019/1/31
     **/
    public boolean isExtraWorkday(String calendar) {
        HolidayJudge.isValidDate(calendar);
        if (extraWorkdays.contains(calendar)) {
            return true;
        }
        return false;
    }

    /**
     * @author  qyw
     * @description  判断是否是休息日（包含法定节假日和不需要补班的周末）
     * @date Created in 21:06 2019/1/31
     **/
    public boolean isHoliday(String calendar) {
        HolidayJudge.isValidDate(calendar);
        // 首先法定节假日必定是休息日
        if (this.isLawHoliday(calendar)) {
            return true;
        }
        // 排除法定节假日外的非周末必定是工作日
        if (!this.isWeekends(calendar)) {
            return false;
        }
        // 所有周末中只有非补班的才是休息日
        if (this.isExtraWorkday(calendar)) {
            return false;
        }
        return true;
    }

    /**
     * @author  qyw
     * @description  校验字符串是否为指定的日期格式,含逻辑严格校验,2007/02/30返回false
     * @date Created in 21:06 2019/1/31
     **/
    private static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
    * @Desc:  手动判断
    * @author: shenliqiang
    * @Time: 2019/10/25 15:39
    * @return
    */

    public static Boolean[] JudgeByLocal(){
        String calendar = LocalDate.now().toString();
        HolidayJudge cc = new HolidayJudge();
        return new Boolean[]{
            cc.isLawHoliday(calendar),
            cc.isExtraWorkday(calendar),
            cc.isHoliday(calendar),
            cc.isWeekends(calendar)
        };
    }

    /**
    * @Desc:  调用api判断
    * @author: shenliqiang
    * @Time: 2019/10/25 15:16
    * @return
    */

    public static int JudgeByApi(){
        String dateStr = LocalDate.now().toString().replace("-","");
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://api.goseek.cn/Tools/holiday?date="+dateStr);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(responseEntity));
                // 正常工作日对应结果为 0,
                // 法定节假日对应结果为 1,
                // 节假日调休补班对应的结果为 2，
                // 休息日对应结果为 3
                return (Integer) jsonObject.get("data");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 5;
    }


    @Test
    public void SynchronizedListTest(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("1");
        copyOnWriteArrayList.remove("1");
    }
}
