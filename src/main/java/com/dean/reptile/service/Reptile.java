package com.dean.reptile.service;

import com.alibaba.fastjson.JSON;
import com.dean.reptile.bean.Accessories;
import com.dean.reptile.util.TimeConversionTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 2017/8/15.
 */
public class Reptile {
    private static final String WEB = "http://www.huzu.com/market/tradelogs/";


    public String getHtml (int num) {
        // 定义一个字符串用来存储网页内容
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        try
        {
            // 将string转成url对象
            URL realUrl = new URL(WEB + num);
            // 初始化一个链接到那个url的连接
            HttpURLConnection connection = (HttpURLConnection )realUrl.openConnection();
            // 开始实际的连接
            int i = connection.getResponseCode();
            if (i != 200) {
                System.out.println("this Url:" + WEB + num + "  --> response code:" + i);
                return null;
            }
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null)
            {
                // 遍历抓取到的每一行并将其存储到result里面
                result += line;
            }
        } catch (Exception e)
        {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally来关闭输入流
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public Accessories parseHtml (String str, int huzu) {
        Accessories accessories = new Accessories();

        Document doc = Jsoup.parse(str);

        try {
            Elements heros = doc.select("p.mb-10");
            if (heros.size() >= 2) {
                Element hero = heros.get(1);
                String[] value = hero.text().split(":");
                accessories.setHero(value[1]);
            }

            Element name = doc.select("p.name").select(".mb-20").first();
            Element price = doc.select("span.ft-ora-n").first();
            Element e = doc.select("p.fl").select(".name-ellipsis-180").first();
            if (e != null) {
                Element e1 = e.parent();
                Element dealPrice = e1.nextElementSibling();
                if (dealPrice != null) {
                    accessories.setDealPrice(Double.valueOf(dealPrice.text()));
                }
                Element dealTime = dealPrice.nextElementSibling().nextElementSibling().nextElementSibling();
                if (dealTime != null) {
                    accessories.setDealTime(dealTime.text());
                }
            }





            if (name != null) {
                accessories.setName(name.text());
            }
            if (price != null) {
                accessories.setPrice(Double.valueOf(price.text().substring(1)));
            }
        } catch (Exception e) {
            System.out.println("this error:" + huzu);
            return null;
        }

//        System.out.println(name.text() + price.text() + dealTime.text() + hero.text());

        accessories.setQueryTime(TimeConversionTool.unixTimeToString(System.currentTimeMillis()));
        accessories.setHuzuId(huzu);
        System.out.println(JSON.toJSON(accessories));
        return accessories;
    }

    public static void main(String[] args) {
        Reptile re = new Reptile();
        re.parseHtml(re.getHtml(2463), 2463);
    }
}
