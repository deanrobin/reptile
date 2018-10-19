package com.dean.reptile.util;

import com.dean.reptile.analyze.C5;
import com.dean.reptile.bean.CrawlRecord;
import com.dean.reptile.bean.own.WebResult;
import com.dean.reptile.db.CrawlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpClient {

    @Autowired
    CrawlMapper crawlMapper;
    @Value("${crawl.html.record}")
    private boolean recording;

    private static Logger log = LoggerFactory.getLogger(HttpClient.class);

    //private HttpClient() {}
    //private static HttpClient httpClient = null;
    //
    //public static HttpClient instance() {
    //    if (httpClient == null) {
    //        synchronized (HttpClient.class) {
    //            if (httpClient == null) {
    //                httpClient = new HttpClient();
    //            }
    //        }
    //    }
    //    return httpClient;
    //}

    public WebResult getHtml (String url, String charSet) {
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
            if (charSet != null) {
                connection.setRequestProperty("Accept-Charset", charSet);
                connection.setRequestProperty("contentType", charSet);
            }
            // 设置超时
            connection.setConnectTimeout(1 * 1000);
            connection.setReadTimeout(1 * 1000);
            // 开始实际的连接
            int i = connection.getResponseCode();
            webResult.setCode(i);
            if (i != 200) {
                System.out.println("this Url:" + url + "  --> response code:" + i);
                return webResult;
            }
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = charSet == null ? new BufferedReader(new InputStreamReader(connection.getInputStream())) :
                    new BufferedReader(new InputStreamReader(connection.getInputStream(), charSet));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null) {
                // 遍历抓取到的每一行并将其存储到result里面
                result += line;
            }
            webResult.setResult(result);
        } catch (java.net.SocketTimeoutException ste) {
            webResult.setCode(-1);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }

            //
            if (recording) {
                crawlMapper.insert(url, System.currentTimeMillis(), webResult.getCode());
            }
        }
        return webResult;
    }
}
