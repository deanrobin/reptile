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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author dean
 */
@Component
public class HttpClient {

    @Autowired
    CrawlMapper crawlMapper;
    @Value("${crawl.html.record}")
    private boolean recording;
    @Value("${http.client.proxy}")
    private boolean useProxy;

    private static Logger log = LoggerFactory.getLogger(HttpClient.class);

    public static Map<String, String> map = new HashMap<>();

    private static Integer lastRandomNumber = null;

    static {
        // 以后把代理IP配置到数据库
        map.put("47.96.136.190", "8118");
        map.put("117.90.5.192", "9000");
        map.put("112.16.28.103", "8060");
        map.put("180.104.63.78", "9000");
        map.put("139.196.137.255", "8118");

        map.put("183.230.177.118", "8060");
        map.put("115.231.5.230", "44524");
        map.put("182.61.59.147", "9999");
        map.put("119.145.136.126", "8888");
        map.put("123.56.86.158", "8080");

        map.put("118.178.227.171", "80");
        map.put("182.111.64.8", "53364");
        map.put("47.95.213.117", "80");
        map.put("183.129.207.84", "33555");
        map.put("115.218.221.25", "9000");

        map.put("60.171.111.113", "33069");
        map.put("115.223.211.4", "9000");
        map.put("49.51.68.122", "1080");
        map.put("112.25.129.174", "41323");
        map.put("140.207.25.114", "50750");

        map.put("218.198.117.194", "39248");
        map.put("117.131.75.134", "80");
        map.put("121.8.98.196", "80");
        map.put("221.7.255.168", "8080");
        map.put("120.76.77.152", "9999");

        map.put("117.87.177.58", "9000");
    }

    public WebResult getHtml (String url, String charSet) {
        WebResult webResult = new WebResult();
        webResult.setUrl(url);
        // 定义一个字符串用来存储网页内容
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        Map.Entry<String, String> entry = getProxy();
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
            connection.setConnectTimeout(10 * 1000);
            connection.setReadTimeout(10 * 1000);
            // 设置代理IP

            if (entry != null & useProxy) {
                log.info("this proxy:" + entry.getKey() + ":" + entry.getValue());
                System.setProperty("http.proxyHost", entry.getKey());
                System.setProperty("http.proxyPort", entry.getValue());
            }


            // 开始实际的连接
            int i = connection.getResponseCode();
            webResult.setCode(i);
            if (i != 200) {
                System.out.println("this Url:" + url + "  --> response code:" + i);
                log.info("this Url:" + url + "  --> response code:" + i);
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
            log.info("this url socket Time out:" + url);
        } catch (java.net.UnknownHostException e) {
            webResult.setCode(-2);
            log.info("this url unknow host:" + url);
        } catch (Exception e) {
            log.error("this url error -->" + url, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }

            //
            String proxy = useProxy ? entry.getKey() : "localhost";
            if (recording) {
                crawlMapper.insert(url, System.currentTimeMillis(), webResult.getCode(), proxy);
            }
        }
        return webResult;
    }

    // 此方法日后需要重新设计，需要加锁
    public static Map.Entry<String, String> getProxy() {
        Random random = new Random();

        int size = map.entrySet().size();
        int a = random.nextInt(size);

        if (lastRandomNumber != null && lastRandomNumber.equals(a)) {
            getProxy();
        }
        System.out.println(a);
        // 赋值
        lastRandomNumber = a;

        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (a == i) {
                return entry;
            }
            ++i;
        }


        return null;
    }
}
