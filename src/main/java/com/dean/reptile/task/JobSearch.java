package com.dean.reptile.task;

import com.dean.reptile.analyze.NGA;
import com.dean.reptile.bean.own.WebResult;
import com.dean.reptile.db.JobSearchMapper;
import com.dean.reptile.util.ApplicationContextHolder;
import com.dean.reptile.util.Config;
import com.dean.reptile.util.HttpClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class JobSearch implements Job{
    private static final String URL = "http://bbs.nga.cn/read.php?tid=";
    private static final int MAX = 13005000;
    private static final String END = ".html";
    private static final String CHARSET = "GBK";
    @Autowired
    private JobSearchMapper jobSearchMapper;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Config config = Config.instance();
        System.out.println("jobSearche status: " + config.isJobSearchContinue());
        if (config.isJobSearchContinue()) {
            resume ();
        } else {
            start();
        }
    }

    private void resume () {
        jobSearchMapper = ApplicationContextHolder.getContext().getBean(JobSearchMapper.class);

        int stopNum = jobSearchMapper.selectMaxId();
        System.out.println("continue reptile from: " + stopNum);

        for (int i = stopNum; i < MAX; ++i) {
            WebResult webResult = HttpClient.instance().getHtml(URL + i, CHARSET);
            if (webResult.getCode() != 200) {
                continue;
            }
            String str = webResult.getResult().replaceAll("charset=GBK", "charset=utf-8");
            NGA nga = new NGA(webResult.getResult());
            String title = nga.getJobSearch();
            try {
                if (title == null) {
                    jobSearchMapper.insert(i, URL + i, 0);
                } else if (title.equals("提示信息")) {
                    jobSearchMapper.insert(i, URL + i, 404);
                } else if(title.matches(".*主宰之剑.*")){
                    jobSearchMapper.insert(i, URL + i, 200);
                } else {
                    jobSearchMapper.insert(i, URL + i, 201);
                }
            } catch (Exception e) {
                System.out.println(URL + i);
                e.printStackTrace();
            }

        }
    }

    private void start() {
        jobSearchMapper = ApplicationContextHolder.getContext().getBean(JobSearchMapper.class);
        System.out.println("clear data first");
        jobSearchMapper.delete();

        for (int i = MAX; i > 0; --i) {
            WebResult webResult = HttpClient.instance().getHtml(URL + i, CHARSET);
            if (webResult.getCode() != 200) {
                continue;
            }
            String str = webResult.getResult().replaceAll("charset=GBK", "charset=utf-8");
            NGA nga = new NGA(webResult.getResult());
            String title = nga.getJobSearch();
            try {
                if (title == null) {
                    jobSearchMapper.insert(i, URL + i, 0);
                } else if (title.equals("提示信息")) {
                    jobSearchMapper.insert(i, URL + i, 404);
                } else if(title.matches(".*主宰之剑.*")){
                    jobSearchMapper.insert(i, URL + i, 200);
                } else {
                    jobSearchMapper.insert(i, URL + i, 201);
                }
            } catch (Exception e) {
                System.out.println(URL + i);
                e.printStackTrace();
            }

        }
    }
}
