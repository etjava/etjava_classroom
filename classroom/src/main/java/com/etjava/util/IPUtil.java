package com.etjava.util;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取IP对应的城市名称 工具类
 */
public class IPUtil {

    /**
     * 根据IP地址获取对应的城市名称
     * @param ipaddr
     * @return 国家+省份+城市
     * 参考API： https://www.ip-api.com/docs/api:json#test
     */
    public static String cityName(String ipaddr){
        StringBuffer buf = new StringBuffer();
        try {
            URL url = new URL("http://ip-api.com/json/"+ipaddr+"?lang=zh-CN");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            JSONObject jsonObject = JSONObject.fromObject(response.toString());
            buf.append(jsonObject.getString("country")+",")
                    .append(jsonObject.getString("regionName")+",")
                    .append(jsonObject.getString("city"));
            System.out.println("登录地址 => "+buf.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

}
