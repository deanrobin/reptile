package com.dean;

import com.dean.reptile.bean.JobRecord;
import com.dean.reptile.db.JobRecordMapper;
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

    @Test
    public void insert() {
        JobRecord jobRecord = new JobRecord();
        jobRecord.setTimestamp(System.currentTimeMillis());
        jobRecord.setStatus(1);
        jobRecord.setType("自动");
        jobRecord.setUser("");

        System.out.println(jobRecordMapper.insert(jobRecord));
    }
}
