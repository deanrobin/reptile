package com.dean;

import com.dean.reptile.mail.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {
    @Autowired
    Email email;

    @Test
    public void sendEmail() {
        String strs[] = {"ladeanrobin@163.com"};
//        email.sendSimpleMail(strs, "试试<ladeanrobin@163.com>", null, "");
        email.sendSimpleMail(strs, "试试<yiranwenchengzhi@163.com>", null, "");
    }
}
