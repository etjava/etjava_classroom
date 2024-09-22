package com.etjava.classroom;

// 在Java中，你可以使用java.net.HttpURLConnection或org.apache.httpcomponents.httpclient库来发送HTTP请求。
// 以下是使用HttpURLConnection发送GET请求的示例：

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestExample {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://ip-api.com/json/8.134.112.252?lang=zh-CN");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println(response.toString());

            JSONObject jsonObject = JSONObject.fromObject(response.toString());
            System.out.println(jsonObject.getString("country"));
            System.out.println(jsonObject.getString("regionName"));
            System.out.println(jsonObject.getString("city"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
