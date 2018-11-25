package com.dean;

import com.alibaba.fastjson.JSON;
import com.dean.reptile.bean.Jewelry;
import com.dean.reptile.bean.JobRecord;
import com.dean.reptile.bean.TaskList;
import com.dean.reptile.db.HeroMapper;
import com.dean.reptile.db.JewelryMapper;
import com.dean.reptile.db.JobRecordMapper;
import com.dean.reptile.db.TaskMapper;
import com.dean.reptile.service.impl.C5JewelrySpider;
import com.dean.reptile.util.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBTest {

    @Autowired
    JobRecordMapper jobRecordMapper;

    @Autowired
    HeroMapper heroMapper;

    @Autowired
    JewelryMapper jewelryMapper;

    @Autowired
    C5JewelrySpider c5JewelrySpider;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    HttpClient httpClient;


    @Test
    public void insert() {
        JobRecord jobRecord = new JobRecord();
        jobRecord.setTimestamp(System.currentTimeMillis());
        jobRecord.setStatus(1);
        jobRecord.setType("自动");
        jobRecord.setUser("");

//        System.out.println(jobRecordMapper.insert(jobRecord));

//        System.out.println(heroMapper.insert("asdfa"));

//        System.out.println(heroMapper.select("ab"));

//        System.out.println(heroMapper.update(1, "abc"));
//        c5JewelrySpider.updateJewelryListByTaskList();
//        c5JewelrySpider.crawlHistory();
//        c5JewelrySpider.fetchBuy();
        c5JewelrySpider.fetchSell();
//        System.out.println(
//                JSON.toJSON(
//                        jewelryMapper.selectJewelryStatusById(6)));
//        c5JewelrySpider.crawlHistory();

//        System.out.println(JSON.toJSON(taskMapper.selectByIndex("553434741", "C5")));

    }

    @Test
    public void testJewelry() {
        c5JewelrySpider.updateJewelryList();
    }

    @Test
    public void testHtml() {
        String url = "https://www.c5game.com/api/product/purchase.json?id=553399785&page=1";

        System.out.println(httpClient.getHtml(url, "null").getResult());;
    }
}
