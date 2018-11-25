package com.dean.reptile.task.C5Task;

import com.dean.reptile.service.date.JewelryService;
import com.dean.reptile.service.impl.C5JewelrySpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JewelryTask {
    private static Logger log = LoggerFactory.getLogger(JewelryTask.class);
    @Autowired
    C5JewelrySpider c5JewelrySpider;
    @Autowired
    JewelryService jewelryService;

//    @Scheduled(fixedRate = 7 * 24 * 60 * 60 * 1000)
    public void updateJewelryList() {
        log.info("start update JeweLry List, the task begin");
        c5JewelrySpider.updateJewelryList();
    }

    @Scheduled(cron = "0 30 9,14,23 * * *")
    public void updateJewelryListByTaskList() {
        log.info("start update JeweLry List By Task List, the task begin");
        c5JewelrySpider.updateJewelryListByTaskList();
    }

    @Scheduled(fixedRate = 1 * 60 * 1000)
    public void fetchTransaction() {
//        log.info("crawel transaction");
        c5JewelrySpider.crawlHistory();
    }

    //@Scheduled(fixedRate = 10 * 60 * 1000)
    public void noticeNewTx() {
        jewelryService.noticeNewTx();
//        log.info("start update JeweLry List By Task List, the task begin");
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void fetchBuy() {
        c5JewelrySpider.fetchBuy();
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void fetchSell() {
        c5JewelrySpider.fetchSell();
    }
}
