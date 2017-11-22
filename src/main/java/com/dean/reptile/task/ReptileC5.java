package com.dean.reptile.task;


import com.dean.reptile.analyze.C5;
import com.dean.reptile.bean.Accessories;
import com.dean.reptile.bean.Transaction;
import com.dean.reptile.bean.own.WebResult;
import com.dean.reptile.db.AccessoriesMapper;
import com.dean.reptile.db.TransactionMapper;
import com.dean.reptile.util.ApplicationContextHolder;
import com.dean.reptile.util.HttpClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReptileC5 implements Job {
    @Autowired
    private AccessoriesMapper accessoriesMapper;
    @Autowired
    private TransactionMapper transactionMapper;

    private static Logger logger = LoggerFactory.getLogger(ReptileC5.class);
    private static final String URL = "https://www.c5game.com/dota/history/";
    private static final int MAX = 10000;
    private static final String END = ".html";
    private static final String SOURCE = "C5";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        accessoriesMapper = ApplicationContextHolder.getContext().getBean(AccessoriesMapper.class);
        transactionMapper = ApplicationContextHolder.getContext().getBean(TransactionMapper.class);
        for (int i = 0; i < MAX; ++i) {
            WebResult webResult = HttpClient.instance().getHtml(URL + "1970" + END);
            if (webResult.getCode() != 200) {
                continue;
            }
            C5 c5 = new C5(webResult.getResult());
            Accessories accessories = c5.getAccessories();
            if (accessories == null) {
                continue;
            }


            List<Accessories> list = accessoriesMapper.findAll(accessories);
            if (list.size() == 0) {
                long now = System.currentTimeMillis();
                accessories.setCreateTime(now);
                accessories.setLastUpdated(now);
                accessories.setSource(SOURCE);
                accessories.setSubscribe(false);
                accessories.setUrl(webResult.getUrl());
                accessoriesMapper.insert(accessories);
            } else {
                int id = list.get(0).getId();
                accessoriesMapper.updatePrice(id, System.currentTimeMillis(), accessories.getPrice());
                // 后面只需要accessories的id和hero属性
                accessories = list.get(0);
            }

            insertTransaction(c5, accessories);
        }
    }

    private void insertTransaction(C5 c5, Accessories accessories) {
        Transaction transaction = c5.getTransaction();
        if (transaction == null) {
            return;
        }
        transaction.setAccessoriesId(accessories.getId());
        transaction.setHero(accessories.getHero());
        List<Transaction> transactionList = transactionMapper.findAll(transaction);
        if (transactionList.size() == 0) {
            transaction.setTransactionNumber(1);
            transactionMapper.insert(transaction);
            insertTransaction(c5, accessories);
        } else {

        }
    }
}
