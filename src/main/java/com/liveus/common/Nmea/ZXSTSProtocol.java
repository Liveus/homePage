package com.liveus.common.Nmea;

import lombok.Data;

import java.time.LocalTime;

/**
 * @Desc:ZXSTS报文
 * @author: Lenovo
 * @Time: 2019/11/21 15:33
 * @Copyright: © Liveus
 * @Warning: for fun
 */
@Data
public class ZXSTSProtocol {
    //协议名称
    private String protocolName;
    //版本号
    private String version;
    //设备编号
    private String deviceId;
    //UTC时间
    private LocalTime utcTime;
    //电压
    private int voltage;
    //电流
    private int Current;
    //当前电量
    private int CurrentElectricity;
    //总容量
    private int FullElectricity;
    //温度
    private float temperature;
    //校验
    private String validation;
}
