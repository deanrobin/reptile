package com.dean;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.http.HttpEntity;

public class HttpTest {

    public static void main(String[] args) throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置连接超时时间
        builder.connectTimeout(1, TimeUnit.MINUTES);
        //设置代理,需要替换
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("124.94.196.188", 9999));
        builder.proxy(proxy);
        Map<String, String> map = new HashMap<>();
        map.put("User-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        map.put("Accept-Language", "zh-CN,zh;q=0.8");
        Headers headers = Headers.of(map);
        Request cookieRequest = new Request.Builder()
            .headers(headers)
            .url("https://www.c5game.com/dota/history/553467668.html")
            .get()
            .build();
        Response execute =  builder.build().newCall(cookieRequest).execute();
        ResponseBody body = execute.body();
        System.out.println(body.string());
        execute.close();

    }
}
