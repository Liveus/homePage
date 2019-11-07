package com.liveus.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/1 14:15
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
public class ObjectUtils {
    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    private static final String JAVAP = "java.";
    private static final String JAVADATESTR = "java.util.Date";

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>(15);
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                logger.error("调用objectToMap异常", e);
            }
            map.put(fieldName, value);
        }
        return map;
    }

    /**
     * 利用递归调用将Object中的值全部进行获取
     *
     * @param timeFormatStr 格式化时间字符串默认<strong>2017-03-10 10:21</strong>
     * @param obj           对象
     * @param excludeFields 排除的属性
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMapString(String timeFormatStr, Object obj, String... excludeFields) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>(15);

        if (excludeFields.length!=0){
            List<String> list = Arrays.asList(excludeFields);
            objectTransfer(timeFormatStr, obj, map, list);
        }else{
            objectTransfer(timeFormatStr, obj, map,null);
        }
        return map;
    }


    /**
     * 递归调用函数
     *
     * @param obj           对象
     * @param map           map
     * @param excludeFields 对应参数
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String, String> objectTransfer(String timeFormatStr, Object obj, Map<String, String> map, List<String> excludeFields) throws IllegalAccessException {
        boolean isExclude=false;
        //默认字符串
        String formatStr = "YYYY-MM-dd HH:mm:ss";
        //设置格式化字符串
        if (timeFormatStr != null && !timeFormatStr.isEmpty()) {
            formatStr = timeFormatStr;
        }
        if (excludeFields!=null){
            isExclude=true;
        }
        Class<?> clazz = obj.getClass();
        //获取值
        for (Field field : clazz.getDeclaredFields()) {
            String fieldName = clazz.getSimpleName() + "." + field.getName();
            //判断是不是需要跳过某个属性
            if (isExclude&&excludeFields.contains(fieldName)){
                continue;
            }
            //设置属性可以被访问
            field.setAccessible(true);
            Object value = field.get(obj);
            Class<?> valueClass = value.getClass();
            if (valueClass.isPrimitive()) {
                map.put(fieldName, value.toString());
            } else if (valueClass.getName().contains(JAVAP)) {
                //判断是不是基本类型
                if (valueClass.getName().equals(JAVADATESTR)) {
                    //格式化Date类型
                    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
                    Date date = (Date) value;
                    String dataStr = sdf.format(date);
                    map.put(fieldName, dataStr);
                } else {
                    map.put(fieldName, value.toString());
                }
            } else {
                objectTransfer(timeFormatStr, value, map,excludeFields);
            }
        }
        return map;
    }

    /**
     * 将source对象转换为clazz类型的对象
     * @author:zhengs
     * @Time: 19-1-21 上午11:18
     * @param source 原始对象
     * @param clazz 转换后的对象类型
     * @return
     */
    public static <T> T toObject(Object source,Class<T> clazz){
        try {
            T newObj = clazz.newInstance();
            BeanUtils.copyProperties(source, newObj);
            return newObj;
        } catch (InstantiationException e) {
            logger.error("toObject is instantiation", e);
        } catch (IllegalAccessException e) {
            logger.error("toObject is IllegalAccess", e);
        }

        return null;
    }

    /**
     * 将list转换为制定clazz类型list集合
     * @author:lsw
     * @Time: 19-1-21 上午11:19
     * @param list 原始list
     * @param clazz 转后后的List对象类型
     * @return
     */
    public static <T> List<T> toList(List list, Class<T> clazz){
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        return JSONObject.parseArray(jsonArray.toJSONString(), clazz);
    }
}
