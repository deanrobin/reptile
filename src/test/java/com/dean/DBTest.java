package com.dean;

import com.dean.reptile.bean.Jewelry;
import com.dean.reptile.bean.JobRecord;
import com.dean.reptile.db.HeroMapper;
import com.dean.reptile.db.JewelryMapper;
import com.dean.reptile.db.JobRecordMapper;
import com.dean.reptile.service.impl.C5JewelrySpider;
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

        System.out.println(heroMapper.update(1, "bc"));
    }

    @Test
    public void testJewelry() {
        c5JewelrySpider.updateJewelryList();
    }
}
