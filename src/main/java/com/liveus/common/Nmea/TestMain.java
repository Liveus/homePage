package com.liveus.common.Nmea;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/20 15:14
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class TestMain {
    public static void main(String[] args) {
        String GPGGA_Test = "$GPGGA,025828.00,2310.3820800,N,11326.0101500,E,1,04,6.0,53.40,M,0.0,M,99,AAAA*5A";
        NMEA nmea = new NMEA();
        nmea.parse(GPGGA_Test);
        System.out.println(nmea.position);
    }
}
