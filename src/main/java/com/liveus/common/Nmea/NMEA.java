package com.liveus.common.Nmea;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/20 14:54
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class NMEA {

    interface SentenceParser {
        void parse(String [] tokens, GPSPosition position);
    }

    // utils
    private static float Latitude2Decimal(String lat, String NS) {
        float med = Float.parseFloat(lat.substring(2))/60.0f;
        med +=  Float.parseFloat(lat.substring(0, 2));
        if(NS.startsWith("S")) {
            med = -med;
        }
        return med;
    }

    private static float Longitude2Decimal(String lon, String WE) {
        float med = Float.parseFloat(lon.substring(3))/60.0f;
        med +=  Float.parseFloat(lon.substring(0, 3));
        if(WE.startsWith("W")) {
            med = -med;
        }
        return med;
    }

    // parsers
    class GPGGA implements SentenceParser {
        public void parse(String [] tokens, GPSPosition position) {
            position.protocolType = tokens[0];
            String[] timestamp  = tokens[1].split("\\.");
            position.time = LocalTime.of(Integer.parseInt(timestamp[0].substring(0,2)),Integer.parseInt(timestamp[0].substring(2,4)),Integer.parseInt(timestamp[0].substring(4,6)));
            position.lat = Latitude2Decimal(tokens[2], tokens[3]);
            position.lon = Longitude2Decimal(tokens[4], tokens[5]);
            position.quality = Integer.parseInt(tokens[6]);
            position.SatelliteSum = Integer.parseInt(tokens[7]);
            position.SatelliteQuality = Float.parseFloat(tokens[8]);
            position.altitude = Float.parseFloat(tokens[9]);
            position.delay = Integer.parseInt(tokens[13]);
            position.siteName = tokens[14].split("\\*")[0];
            position.validation = tokens[14].split("\\*")[1];
        }
    }

    public class GPSPosition {
        private String protocolType = "";//协议名称
        public LocalTime time ;//UTC时间
        private float lat = 0.0f;//纬度
        private float lon = 0.0f;//经度
        private int quality = 0;//解状态
        private int SatelliteSum = 0;//参与计算的卫星
        private float SatelliteQuality = 0;//卫星分布质量
        private float altitude = 0.0f;//高程
        private int delay = 0;//外接分差延迟，秒
        private String siteName = "";//基站号
        private String validation = "";//校验

        @Override
        public String toString() {
            return "GPSPosition{" +
                    "protocolType='" + protocolType + '\'' +
                    ", time=" + time +
                    ", lat=" + lat +
                    ", lon=" + lon +
                    ", quality=" + quality +
                    ", SatelliteSum=" + SatelliteSum +
                    ", SatelliteQuality=" + SatelliteQuality +
                    ", altitude=" + altitude +
                    ", delay=" + delay +
                    ", siteName='" + siteName + '\'' +
                    ", validation='" + validation + '\'' +
                    '}';
        }
    }

    GPSPosition position = new GPSPosition();

    private static final Map<String, SentenceParser> sentenceParsers = new HashMap<>();

    public NMEA() {
        sentenceParsers.put("GPGGA", new GPGGA());
    }

    public GPSPosition parse(String line) {
        if(line.startsWith("$")) {
            String nmea = line.substring(1);
            String[] tokens = nmea.split(",");
            String type = tokens[0];
            //TODO check crc
            if(sentenceParsers.containsKey(type)) {
                sentenceParsers.get(type).parse(tokens, position);
            }
        }
        return position;
    }
}
