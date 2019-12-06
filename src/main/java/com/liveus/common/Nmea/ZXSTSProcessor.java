package com.liveus.common.Nmea;

import java.time.LocalTime;

/**
 * @Desc:ZXSTS解析
 * @author: Lenovo
 * @Time: 2019/11/21 15:40
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class ZXSTSProcessor {
    public static void main(String[] args) {
        String report = "$ZXSTS,1,1234,025820,3936,-219,4064,5558,22.3*60";
        ZXSTSProtocol zxstsProtocol = new ZXSTSProtocol();
        report = report.substring(1);
        String[] toekns = report.split(",");
        zxstsProtocol.setProtocolName(toekns[0]);
        zxstsProtocol.setVersion(toekns[1]);
        zxstsProtocol.setDeviceId(toekns[2]);
        zxstsProtocol.setUtcTime(LocalTime.of(Integer.parseInt(toekns[3].substring(0,2)),Integer.parseInt(toekns[3].substring(2,4)),Integer.parseInt(toekns[3].substring(4,6))));
        zxstsProtocol.setVoltage(Integer.parseInt(toekns[4]));
        zxstsProtocol.setCurrent(Integer.parseInt(toekns[5]));
        zxstsProtocol.setCurrentElectricity(Integer.parseInt(toekns[6]));
        zxstsProtocol.setFullElectricity(Integer.parseInt(toekns[7]));
        zxstsProtocol.setTemperature(Float.parseFloat(toekns[8].split("\\*")[0]));
        zxstsProtocol.setValidation(toekns[8].split("\\*")[1]);
        System.out.println(zxstsProtocol);
    }
}
