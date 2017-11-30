package com.dean.reptile.task;

import org.quartz.SchedulerException;

public class QuartzClient extends Thread {
    private static QuartzClient quartzClient = null;
    private static Integer day = 24 * 60 * 60;

    public static QuartzClient instance() {
        if (quartzClient == null) {
            synchronized (QuartzClient.class) {
                if (quartzClient == null) {
                    quartzClient = new QuartzClient();
                }
            }
        }
        return quartzClient;
    }

    private QuartzClient(){

    }

    public void init(Integer seconds) {
        try {
            QuartzUtil.addJob("init", "reptileC5Init", ReptileC5.class, seconds == null || seconds < 60 * 60 ? day : seconds);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void addJob() {
        try {
            QuartzUtil.addJob("job", "reptileC5", ReptileC5.class, null);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void getPurchaseIntent() {
        try {
            QuartzUtil.addJob("buy", "purchaseIntent", PurchaseIntent.class, null);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void getNGA() {
        try {
            QuartzUtil.addJob("nga", "jobSearch", JobSearch.class, null);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//            QuartzUtil.addJob("job1", "tigger1", QuartzTest.class, 10);
//            QuartzUtil.addJob("job2", "tigger2", QuartzTest2.class, null);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//    }

}
