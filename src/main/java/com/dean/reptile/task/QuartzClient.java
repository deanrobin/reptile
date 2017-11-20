package com.dean.reptile.task;

import org.quartz.SchedulerException;

public class QuartzClient extends Thread {
    private static QuartzClient quartzClient = null;

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

    public void addJob() {
        try {
            QuartzUtil.addJob("job", "reptileC5", ReptileC5.class, null);
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
