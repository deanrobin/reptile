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



//
//    public Accessories parseHtml (String str, int huzu) {
//        Accessories accessories = new Accessories();
//
//        Document doc = Jsoup.parse(str);
//
//        try {
//            Elements heros = doc.select("p.mb-10");
//            if (heros.size() >= 2) {
//                Element hero = heros.get(1);
//                String[] value = hero.text().split(":");
//                accessories.setHero(value[1]);
//            }
//
//            Element name = doc.select("p.name").select(".mb-20").first();
//            Element price = doc.select("span.ft-ora-n").first();
//            Element e = doc.select("p.fl").select(".name-ellipsis-180").first();
//            if (e != null) {
//                Element e1 = e.parent();
//                Element dealPrice = e1.nextElementSibling();
//                if (dealPrice != null) {
//                    accessories.setDealPrice(Double.valueOf(dealPrice.text()));
//                }
//                Element dealTime = dealPrice.nextElementSibling().nextElementSibling().nextElementSibling();
//                if (dealTime != null) {
//                    accessories.setDealTime(dealTime.text());
//                }
//            }
//
//
//
//
//
//            if (name != null) {
//                accessories.setName(name.text());
//            }
//            if (price != null) {
//                accessories.setPrice(Double.valueOf(price.text().substring(1)));
//            }
//        } catch (Exception e) {
//            System.out.println("this error:" + huzu);
//            return null;
//        }
//
////        System.out.println(name.text() + price.text() + dealTime.text() + hero.text());
//
//        accessories.setQueryTime(TimeConversionTool.unixTimeToString(System.currentTimeMillis()));
//        accessories.setHuzuId(huzu);
//        System.out.println(JSON.toJSON(accessories));
//        return accessories;
//    }
//
//    public static void main(String[] args) {
//        Reptile re = new Reptile();
//        re.parseHtml(re.getHtml(2463), 2463);
//    }
}
