package com.dean.reptile.service.impl;

import com.alibaba.fastjson.JSON;
import com.dean.reptile.analyze.C5;
import com.dean.reptile.bean.Jewelry;
import com.dean.reptile.bean.own.WebResult;
import com.dean.reptile.constant.EmailSubjectEnum;
import com.dean.reptile.constant.HeroCache;
import com.dean.reptile.constant.StatusEnum;
import com.dean.reptile.db.HeroMapper;
import com.dean.reptile.db.JewelryMapper;
import com.dean.reptile.service.SpiderService;
import com.dean.reptile.util.HttpClient;
import com.dean.reptile.util.TimeTool;
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
    private static final int MAX = 100000;
    private static final String SOURCE = "C5";

    private static Logger log = LoggerFactory.getLogger(C5JewelrySpider.class);

    @Override
    public String getEmailSubject() {
        return EmailSubjectEnum.UPDATE_JEWELRY_LIST.getSubject();
    }

    public void updateJewelryList() {
        if (HERO_SET == null) {
            HERO_SET = HeroCache.getHeroSet();
        }

        Long begin = System.currentTimeMillis();

        StringBuilder falseString = new StringBuilder();
        int falseNum = 0;
        StringBuilder errorString = new StringBuilder();
        int errorNum = 0;

        for (int i = 0; i < MAX; ++i) {
            String url = URL + i + END;
//            String url = URL + "5567" + END;
            try {
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

//                System.out.println(JSON.toJSON(jewelry));

                    result = jewelryMapper.insert(jewelry) == 1;
                } else {
                    jewelry.setLastTime(timestamp);
                    result = jewelryMapper.updateLastPrice(jewelry) == 1;
                }

                if (!result) {
                    log.error("this result is false:" + url);
                    falseString.append("this url result is false:" + url + System.lineSeparator());
                    falseNum ++;
                }
            } catch (Exception e) {
                log.error("", e);
                errorString.append("this url result has exception:" + url + System.lineSeparator());
                errorNum ++;
            }

            String content = initEmailText(begin, falseString, falseNum, errorString, errorNum);
            email.sendEmail(getEmailSubject(), content);
        }
    }

    private String initEmailText(Long beginTime, StringBuilder falseText, int falseNum, StringBuilder errorText, int errorNum) {
        StringBuilder stringBuilder = new StringBuilder("已完成爬虫update Jewelry列表任务");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("任务起始时间:" + TimeTool.unixTimeToString(beginTime));
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("任务结束时间:" + TimeTool.getNow());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("插入数据库失败条数:" + falseNum);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("程序异常报错的URL数量:" + errorNum);
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("以下是插入数据库失败任务:" + System.lineSeparator());
        stringBuilder.append(falseText);

        stringBuilder.append("以下是程序异常的任务:" + System.lineSeparator());
        stringBuilder.append(errorText);

        return stringBuilder.toString();
    }
}
