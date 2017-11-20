package com.dean.reptile.util;

import com.dean.reptile.bean.own.WebResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    private HttpClient() {}
    private static HttpClient httpClient = null;

    public static HttpClient instance() {
        if (httpClient == null) {
            synchronized (HttpClient.class) {
                if (httpClient == null) {
                    httpClient = new HttpClient();
                }
            }
        }
        return httpClient;
    }

    public WebResult getHtml (String url) {
        WebResult webResult = new WebResult();
        webResult.setUrl(url);
        // 定义一个字符串用来存储网页内容
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        try {
            // 将string转成url对象
            URL realUrl = new URL(url);
            // 初始化一个链接到那个url的连接
            HttpURLConnection connection = (HttpURLConnection )realUrl.openConnection();
            connection.setRequestProperty("User-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            // 开始实际的连接
            int i = connection.getResponseCode();
            webResult.setCode(i);
            if (i != 200) {
                System.out.println("this Url:" + url + "  --> response code:" + i);
                return webResult;
            }
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null) {
                // 遍历抓取到的每一行并将其存储到result里面
                result += line;
            }
            webResult.setResult(result);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return webResult;
    }
}
