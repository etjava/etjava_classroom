package com.etjava.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

public class HelloWorld {

    @Value("${website}")
    private String website;

    private static String weburl = "http://www.cnblogs.com/";

    public static void main(String[] args) {
        CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        HttpGet httpget = new HttpGet(weburl); // 创建httpget实例
        CloseableHttpResponse response=null;
        try {
            response = httpClient.execute(httpget);
        } catch (ClientProtocolException e) {  // http协议异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) { // io异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 执行get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        try {
            System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8"));
        } catch (ParseException e) {  // 解析异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) { // io异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 指定编码打印网页内容
        try {
            response.close();
        } catch (IOException e) {  // io异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 关闭流和释放系统资源
    }
}
