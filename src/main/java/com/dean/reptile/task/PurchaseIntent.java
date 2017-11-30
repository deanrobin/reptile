package com.dean.reptile.task;

import com.dean.reptile.bean.Accessories;
import com.dean.reptile.bean.own.WebResult;
import com.dean.reptile.db.AccessoriesMapper;
import com.dean.reptile.util.ApplicationContextHolder;
import com.dean.reptile.util.HttpClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PurchaseIntent implements Job {
    @Autowired
    private AccessoriesMapper accessoriesMapper;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        accessoriesMapper = ApplicationContextHolder.getContext().getBean(AccessoriesMapper.class);
        List<Accessories> list = accessoriesMapper.findSubcribe();
        for (Accessories accessories : list) {
            String url = getNewUrl(accessories.getUrl());
            WebResult webResult = HttpClient.instance().getHtml(url, null);
            if (webResult.getCode() != 200) {
                continue;
            }
            

            System.out.print(url);
        }
    }


    private String getNewUrl(String url) {
        String[] urls = url.split("history");
        String[] ends = urls[1].split("\\.");
        String newUrl = urls[0] + ends[0].substring(1) + "-P." + ends[1];
        return newUrl;
    }
}
