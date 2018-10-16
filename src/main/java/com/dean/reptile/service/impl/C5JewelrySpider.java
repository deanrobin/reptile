package com.dean.reptile.service.impl;

import com.alibaba.fastjson.JSON;
import com.dean.reptile.analyze.C5;
import com.dean.reptile.bean.Jewelry;
import com.dean.reptile.bean.own.WebResult;
import com.dean.reptile.constant.HeroCache;
import com.dean.reptile.constant.StatusEnum;
import com.dean.reptile.db.HeroMapper;
import com.dean.reptile.db.JewelryMapper;
import com.dean.reptile.service.SpiderService;
import com.dean.reptile.util.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class C5JewelrySpider extends SpiderService {

    @Autowired
    private HeroMapper heroMapper;
    @Autowired
    private JewelryMapper jewelryMapper;

    private static Set<String> HERO_SET = HeroCache.getHeroSet();

    private static final String URL = BASE_URL + "history/";
    private static final int MAX = 10000;
    private static final String SOURCE = "C5";

    private static Logger log = LoggerFactory.getLogger(C5JewelrySpider.class);


    public void updateJewelryList() {
        if (HERO_SET == null) {
            HERO_SET = HeroCache.getHeroSet();
        }

        for (int i = 0; i < 1; ++i) {
            String url = URL + "7900" + END;
//            String url = URL + "5567" + END;
            WebResult webResult = HttpClient.instance().getHtml(url, null);
            if (webResult.getCode() != 200) {
                continue;
            }
            C5 c5 = new C5(webResult.getResult());
            Jewelry jewelry = c5.getJewelry();

            Long timestamp = System.currentTimeMillis();
            if (jewelry.getHeroName().equals("信使")) {

            }

            boolean result = false;

            if (jewelryMapper.selectByIndex(jewelry.getName(), jewelry.getHeroName()) == null) {
                String heroName = jewelry.getHeroName();

                if (!HERO_SET.contains(heroName) && heroMapper.insert(heroName) == 1) {
                    HERO_SET.add(heroName);
                }

                jewelry.setHtml(url);
                jewelry.setHid(i);
                jewelry.setSource(SOURCE);
                jewelry.setCreateTime(timestamp);
                jewelry.setLastTime(timestamp);

                jewelry.setStatus(StatusEnum.SUCCESS.getCode());
                //type暂时空
                jewelry.setType("");

                System.out.println(JSON.toJSON(jewelry));

                result = jewelryMapper.insert(jewelry) == 1;
            } else {
                jewelry.setLastTime(timestamp);
                result = jewelryMapper.updateLastPrice(jewelry) == 1;
            }

            if (!result) {
                log.error("this result is false:" + url);
            }
        }
    }


//    public static void main(String[] args) {
//        new C5JewelrySpider().updateJewelryList();
//    }
}
