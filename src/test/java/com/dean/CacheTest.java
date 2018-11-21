package com.dean;

import com.alibaba.fastjson.JSON;
import com.dean.reptile.bean.Transaction;
import com.dean.reptile.cache.TxCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {

    @Test
    public void test() {

        T1 t1 = new T1();
        T2 t2 = new T2();

        t1.start();
        t2.start();



    }


    class T1 extends Thread {
        @Override
        public void run() {
            System.out.println("T1 run");
            for (int i= 0; i < 100; ++i) {
                Transaction transaction1 = new Transaction();
                transaction1.setId(i);
                TxCache.instance.add(transaction1);

            }
        }
    }

    class T2 extends Thread {
        @Override
        public void run() {
            System.out.println("I'm running!");

            List<Transaction> t = TxCache.instance.read();
            System.out.println(
                    "fsdf" + t.size()
            );

            System.out.println("end");
        }
    }
}
