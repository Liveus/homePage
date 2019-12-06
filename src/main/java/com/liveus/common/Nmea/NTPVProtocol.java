package com.liveus.common.Nmea;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Desc: NTPV报文
 * @author: Lenovo
 * @Time: 2019/11/21 15:54
 * @Copyright: © Liveus
 * @Warning: for fun
 */
@Data
public class NTPVProtocol {
    //协议名称
    private String protocolName;
    //设备编号
    private String deviceId;
    //时间
    private LocalDateTime time;
    //定位解状态
    private int status;
    //纬度
    private float lat;
    //经度
    private float lon;
    //高度
    private float altitude;
    //速度有效性
    private int isEffective;
    //速度大小
    private float speed;
    //速度方向
    private float velocityDirection;
    //校验
    private String validation;
}
