package com.dean.reptile.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzUtil {
    private final static String JOB_GROUP_NAME = "QUARTZ_JOBGROUP_NAME";// 任务组
    private final static String TRIGGER_GROUP_NAME = "QUARTZ_TRIGGERGROUP_NAME";// 触发器组
    private static Logger log = LoggerFactory.getLogger(QuartzUtil.class);// 日志

    /**
     * 添加任务的方法
     *
     * @param jobName
     *            任务名
     * @param triggerName
     *            触发器名
     * @param jobClass
     *            执行任务的类
     * @param seconds
     *            间隔时间
     * @throws SchedulerException
     */
    public static void addJob(String jobName, String triggerName, Class<? extends Job> jobClass, Integer seconds)
            throws SchedulerException {
        log.info("==================initialization=================");
        // 创建一个SchedulerFactory工厂实例
        SchedulerFactory sf = new StdSchedulerFactory();
        // 通过SchedulerFactory构建Scheduler对象
        Scheduler sche = sf.getScheduler();

        log.info("===================initialize finshed===================");
        log.info("==============add the Job to Scheduler==================");

        // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, JOB_GROUP_NAME).build();
        // 构建一个触发器，规定触发的规则
        Trigger trigger = null;
        if (seconds != null && seconds != 0) {
            trigger = TriggerBuilder.newTrigger()// 创建一个新的TriggerBuilder来规范一个触发器
                    .withIdentity(triggerName, TRIGGER_GROUP_NAME)// 给触发器起一个名字和组名
                    .startNow()// 立即执行
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule() // 执行计划
                            .withIntervalInSeconds(seconds) // 执行间隔： 表
                            .repeatForever()// 一直执行
                    ).build();// 产生触发器
        } else {
            trigger = TriggerBuilder.newTrigger()// 创建一个新的TriggerBuilder来规范一个触发器
                    .withIdentity(triggerName, TRIGGER_GROUP_NAME)// 给触发器起一个名字和组名
                    .startNow()// 立即执行
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule() // 执行计划
                            .withRepeatCount(0)
                    ).build();// 产生触发器
        }


        // 向Scheduler中添加job任务和trigger触发器
        sche.scheduleJob(jobDetail, trigger);

        // 启动
        sche.start();
    }

}
