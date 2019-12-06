package com.liveus.common.Nmea;

import java.time.LocalDateTime;

/**
 * @Desc: NTPV解析
 * @author: Lenovo
 * @Time: 2019/11/21 15:59
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class NTPVProcessor {
    public static void main(String[] args) {
        String report = "$NTPV,0910,20190527040521.0,2,23.17281040,113.43376482,50.175,1,0.008,54.57*0E";
        report = report.substring(1);
        String[] tokens = report.split(",");
        NTPVProtocol ntpvProtocol = new NTPVProtocol();
        ntpvProtocol.setProtocolName(tokens[0]);
        ntpvProtocol.setDeviceId(tokens[1]);
        ntpvProtocol.setTime(LocalDateTime.of(Integer.parseInt(tokens[2].substring(0,4)),Integer.parseInt(tokens[2].substring(4,6)),Integer.parseInt(tokens[2].substring(6,8)),
                Integer.parseInt(tokens[2].substring(8,10)),Integer.parseInt(tokens[2].substring(10,12)),Integer.parseInt(tokens[2].substring(12,14))));
        ntpvProtocol.setStatus(Integer.parseInt(tokens[3]));
        ntpvProtocol.setLat(Float.parseFloat(tokens[4]));
        ntpvProtocol.setLon(Float.parseFloat(tokens[5]));
        ntpvProtocol.setAltitude(Float.parseFloat(tokens[6]));
        ntpvProtocol.setIsEffective(Integer.parseInt(tokens[7]));
        ntpvProtocol.setSpeed(Float.parseFloat(tokens[8]));
        ntpvProtocol.setVelocityDirection(Float.parseFloat(tokens[9].split("\\*")[0]));
        ntpvProtocol.setValidation(tokens[9].split("\\*")[1]);
        System.out.println(ntpvProtocol);
    }
}
