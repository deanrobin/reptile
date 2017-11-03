package com.dean.reptile.task;

import org.quartz.SchedulerException;

public class QuartzClient {

    public static void main(String[] args) {
        try {
            QuartzUtil.addJob("job1", "tigger1", QuartzTest.class, 10);
            QuartzUtil.addJob("job2", "tigger2", QuartzTest2.class, null);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
