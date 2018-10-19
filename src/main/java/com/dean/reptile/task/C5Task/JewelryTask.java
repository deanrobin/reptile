package com.dean.reptile.task.C5Task;

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

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void updateJewelryList() {
        log.info("start update JeweLry List, the task begin");
        c5JewelrySpider.updateJewelryList();
    }
}
