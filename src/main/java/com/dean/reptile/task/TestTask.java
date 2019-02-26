package com.dean.reptile.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class TestTask {

    @Scheduled(fixedDelay = 10 * 1000)
    public void test1() {
        System.out.println("test1");
        try {
            Thread.sleep(60 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 10 * 1000)
    public void test2() {
        System.out.println("test2");
        try {
            Thread.sleep(60 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    @Scheduled(fixedDelay = 10 * 1000)
    public void test3() {
        System.out.println("test3");
        try {
            Thread.sleep(60 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
