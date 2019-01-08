package com.dean.reptile.service.impl;

import com.dean.reptile.analyze.C5;
import com.dean.reptile.analyze.C5JSON;
import com.dean.reptile.bean.Buyer;
import com.dean.reptile.bean.Jewelry;
import com.dean.reptile.bean.JewelryStatus;
import com.dean.reptile.bean.Seller;
import com.dean.reptile.bean.TaskList;
import com.dean.reptile.bean.Transaction;
import com.dean.reptile.bean.ex.JewelryEx;
import com.dean.reptile.bean.own.WebResult;
import com.dean.reptile.constant.EmailSubjectEnum;
import com.dean.reptile.constant.HeroCache;
import com.dean.reptile.constant.StatusEnum;
import com.dean.reptile.db.*;
import com.dean.reptile.service.SpiderService;
import com.dean.reptile.util.SleepTime;
import com.dean.reptile.util.TimeTool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;

/**
 * @author dean
 */
@Service
public class C5JewelrySpider extends SpiderService {

    @Autowired
    private HeroMapper heroMapper;
    @Autowired
    private JewelryMapper jewelryMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private BuyerMapper buyerMapper;
    @Autowired
    private SellerMapper sellerMapper;

    private static Set<String> HERO_SET = HeroCache.getHeroSet();

    private static final String URL = BASE_URL + "history/";
    private static final int MAX = 100000;
    private static final String SOURCE = "C5";
    private static final int INITNUM=0;
    private static final String PREFIX = "https://www.c5game.com/dota/";

    private static Logger log = LoggerFactory.getLogger(C5JewelrySpider.class);

    @Override
    public String getEmailSubject() {
        return EmailSubjectEnum.UPDATE_JEWELRY_LIST.getSubject();
    }

    // 抓取全部
    public void updateJewelryList() {
        if (HERO_SET == null) {
            HERO_SET = heroMapper.selectAll();
        }

        Long begin = System.currentTimeMillis();

        StringBuilder falseString = new StringBuilder();
        int falseNum = 0;
        StringBuilder errorString = new StringBuilder();
        int errorNum = 0;

        for (int i =INITNUM + 1; i < MAX; ++i) {
            String url = URL + i + END;
//            String url = "https://www.c5game.com/dota/history/22.html";
            try {
                Thread.sleep(SleepTime.randomTime());
                WebResult webResult = httpClient.getHtml(url, null);
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

                    result = jewelryMapper.insert(jewelry) == 1;
                    log.info("add jewelry record, id:" + jewelry.getId());

                    if (jewelry.getId() != null) {
                        jewelryMapper.insertJewelryStatus(jewelry.getId(), jewelry.getName());
                    }
                } else {
                    jewelry.setLastTime(timestamp);
                    result = jewelryMapper.updateLastPrice(jewelry) == 1;
                    log.info("update jewelry record, id:" + jewelry.getId());
                }

                if (!result) {
                    log.error("this result is false:" + url);
                    falseString.append("this url result is false:" + url + System.lineSeparator());
                    falseNum ++;
                }
            } catch (Exception e) {
                log.error("c5 jewelry number:" + i, e);
                errorString.append("this url result has exception:" + url + System.lineSeparator());
                errorNum ++;
            }
        }

        // for循环结束 发送邮件
        String content = initEmailText(begin, falseString, falseNum, errorString, errorNum);
        email.sendEmail(getEmailSubject(), content);
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

    public void crawlHistory() {
        List<JewelryStatus> list = jewelryMapper.getNeedHistory();

        if (list == null) {
            log.error("db error, get jewelry need history return null");
            return;
        }

        for (JewelryStatus js : list) {
            //String url = "https://www.c5game.com/dota/history/7900.html";
            try {
                Jewelry jewelry = jewelryMapper.selectJewelryById(js.getId());
                String url = jewelry.getHtml();
                history(url, jewelry.getId());
            } catch (Exception e) {
                log.error("", e);
            }
        }

    }

    public void updateJewelryListByTaskList() {
        if (HERO_SET == null) {
            HERO_SET = heroMapper.selectAll();
        }

        Long begin = System.currentTimeMillis();

        StringBuilder falseString = new StringBuilder();
        int falseNum = 0;
        StringBuilder errorString = new StringBuilder();
        int errorNum = 0;

        List<TaskList> list = taskMapper.selectAll();
        for (TaskList task : list) {

            String url = URL + task.getPageNumber() + END;
//            String url = "https://www.c5game.com/dota/history/22.html";
            try {
                WebResult webResult = httpClient.getOkhttpHtml(url);
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
                    jewelry.setHid(Integer.valueOf(task.getPageNumber()));
                    jewelry.setSource(SOURCE);
                    jewelry.setCreateTime(timestamp);
                    jewelry.setLastTime(timestamp);

                    jewelry.setStatus(StatusEnum.SUCCESS.getCode());
                    //type暂时空
                    jewelry.setType("");

                    result = jewelryMapper.insert(jewelry) == 1;
                    log.info("add jewelry record, id:" + jewelry.getId());

                    if (jewelry.getId() != null) {
                        jewelryMapper.insertJewelryStatus(jewelry.getId(), jewelry.getName());
                    }
                } else {
                    jewelry.setLastTime(timestamp);
                    result = jewelryMapper.updateLastPrice(jewelry) == 1;
                    log.info("update jewelry record, id:" + jewelry.getId());
                }

                if (!result) {
                    log.error("this result is false:" + url);
                    falseString.append("this url result is false:" + url + System.lineSeparator());
                    falseNum ++;
                }
            } catch (Exception e) {
                log.error("c5 jewelry number:" + task.getPageNumber(), e);
                errorString.append("this url result has exception:" + url + System.lineSeparator());
                errorNum ++;
            }
        }

        // for循环结束 发送邮件
        String content = initEmailText(begin, falseString, falseNum, errorString, errorNum);
        email.sendEmail(getEmailSubject(), content);
    }

    public void fetchBuy(Integer id) {
        List<JewelryEx> list = null;
        if (id == null) {
            list = jewelryMapper.getFetchBuy();
        } else {
            JewelryEx ex = jewelryMapper.selectJewelryExById(id);
            if (ex.isCrawlBuy()) {
                list = new ArrayList<>();
                list.add(ex);
            }
        }


        if (list == null) {
            log.error("db error, get jewelry fetch buy return null, maybe id is:" + id);
            return;
        }

        final String suffix = "-P.html";


        for (JewelryEx je : list) {
            try {
                String url = PREFIX + je.getHid() + suffix;
                WebResult webResult = httpClient.getOkhttpHtml(url);
                if (webResult.getCode() != 200) {
                    continue;
                }

                C5 c5 = new C5(webResult.getResult());
                String buyerUrl = c5.getBuyerHttpUrl();
                if (StringUtils.isEmpty(buyerUrl)) {
                    continue;
                }

                //get 请求
                url = C5_URL + buyerUrl;
                webResult = httpClient.getOkhttpHtml(url);
                if (webResult.getCode() != 200) {
                    continue;
                }

                List<Buyer> buyers = C5JSON.getBuyers(webResult.getResult(), je.getId());

                if (buyers == null || buyers.isEmpty()) {
                    continue;
                }

                for (Buyer buyer : buyers) {
                   Buyer dbBuyer = buyerMapper.selectByIndex(buyer.getJewelryId(), buyer.getBuyId());
                   if (dbBuyer != null) {
                       //update
                       buyer.setId(dbBuyer.getId());
                       buyerMapper.updateLastPrice(buyer);
                       continue;
                   }

                   log.info("insert new buyer.");
                   buyerMapper.insert(buyer);
                   // TODO 怎么做价格通知呢？ 用阻塞队列或者数据库遍历 倾向于前者
                }
            } catch (Exception e) {
                log.error("", e);
            }
        }
        log.info("get buyer task over.");
    }


    public void fetchSell(Integer id) {
        List<JewelryEx> list = null;
        if (id == null) {
            list = jewelryMapper.getFetchSell();
        } else {
            JewelryEx ex = jewelryMapper.selectJewelryExById(id);
            if (ex.isCrawlSell()) {
                list = new ArrayList<>();
                list.add(ex);
            }
        }

        if (list == null) {
            log.error("db error, get jewelry fetch sell return null, maybe id:" + id);
            return;
        }

        final String suffix = "-S.html";
        final String str = "&page=";
        List<Seller> sellerList = new ArrayList<>();
        for (JewelryEx je : list) {
            String url = PREFIX + je.getHid() + suffix;
            WebResult webResult = httpClient.getOkhttpHtml(url);
            if (webResult.getCode() != 200) {
                continue;
            }

            C5 c5 = new C5(webResult.getResult());

            String sellerUrl = c5.getSellerHttpUrl();
            if (StringUtils.isEmpty(sellerUrl)) {
                continue;
            }

            Integer page = c5.maxSellerPage();
            // 暂时只收取前两页的， 多了也没有用
            if (page >= 2) {
                page = 2;
            }
            for (int i = 1; i <= page; ++i) {
                String request = C5_URL + sellerUrl + str + i;

                // 好像会出现429状态 再观察
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                webResult = httpClient.getOkhttpHtml(request);
                if (webResult.getCode() != 200) {
                    continue;
                }

                List<Seller> sellers = C5JSON.getSeller(webResult.getResult(), je.getId());
                if (sellers == null) {
                    continue;
                }
                sellerList.addAll(sellers);
            }

        }

        for (Seller seller : sellerList) {
            //TODO 通知
            Seller db = sellerMapper.selectByIndex(seller.getJewelryId(), seller.getSellId());
            if (db != null) {
                seller.setId(db.getId());
                sellerMapper.updateLastPrice(seller);
                continue;
            }

            log.info("insert new seller");
            sellerMapper.insert(seller);

        }

        log.info("get seller task over.");
    }

    public void updateJewelry(List<Integer> listId) {
        // 需要处理的饰品列表
        List<JewelryEx> list = new ArrayList<>();
        for (Integer id : listId) {
            JewelryEx jewelry = jewelryMapper.selectJewelryExById(id);
            list.add(jewelry);
        }
        boolean res = true;
        for (JewelryEx je : list) {
            try {
                // 更新饰品
                String url = je.getHtml();
                WebResult webResult = httpClient.getOkhttpHtml(url);
                if (webResult.getCode() != 200) {
                    res = false;
                    continue;
                }
                C5 c5 = new C5(webResult.getResult());
                Jewelry jewelry = c5.getJewelry();

                Long timestamp = System.currentTimeMillis();
                //if (jewelry.getHeroName().equals("信使")) {
                //
                //}

                jewelry.setLastTime(timestamp);
                jewelryMapper.updateLastPrice(jewelry);

                // 更新历史
                if (je.isCrawlHistory()) {
                    history(url, je.getId());
                }

                if (je.isCrawlBuy()) {
                    fetchBuy(je.getId());
                }

                if (je.isCrawlSell()) {
                    fetchSell(je.getId());
                }
            } catch (Exception e) {
                log.error("Manual updateJewelry error，" ,e);
            }
        }

        email.sendEmail(EmailSubjectEnum.MANUAL_UPDATE_JEWELRY.getSubject(), "饰品id:" + JSON.toJSON(listId) + ", result:" + res);
    }


    private boolean history(String url, Integer jewelryId) {
        WebResult webResult = httpClient.getOkhttpHtml(url);
        if (webResult.getCode() != 200) {
            return false;
        }
        C5 c5 = new C5(webResult.getResult());
        List<Transaction> transactions = c5.getTransactionList(jewelryId);

        for (Transaction tx : transactions) {
            //先查  后插入
            Transaction dbTx = transactionMapper.querySameData(tx);

            if (dbTx == null) {
                log.info("there is new transaction insert into DB");
                transactionMapper.insert(tx);
            }
        }
        return true;
    }

    public void fetchBuy() {
        fetchBuy(null);
    }

    public void fetchSell() {
        fetchSell(null);
    }
}
