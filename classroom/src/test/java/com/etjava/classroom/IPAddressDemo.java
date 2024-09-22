package com.etjava.classroom;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddressDemo {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ipAddress = inetAddress.getHostAddress();
            System.out.println("IP地址：" + ipAddress);

            InetAddress geoIP = InetAddress.getByName(ipAddress);
            String country = geoIP.getHostName();
            System.out.println("实际地址：" + country);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}