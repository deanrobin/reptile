package com.dean.reptile.service;

import com.dean.reptile.bean.Accessories;
import com.dean.reptile.util.DataProcess;

import java.util.List;

/**
 * Created by Admin on 2017/8/16.
 */
public class QueryService {
    private static QueryService instance = new QueryService();

    private QueryService() {

    }

    public static QueryService instance() {
        synchronized (QueryService.class) {
            return instance;
        }
    }

    public List<Accessories> query(String name, String hero, String queryTime) {
        String sql = "select * from accessories where 1=1 ";
        if (name != null && !name.trim().isEmpty()) {
            sql = sql + " and name like'%" + name + "%' ";
        }
        if (hero != null && !hero.trim().isEmpty()) {
            sql = sql + " and hero like'%" + hero + "%' ";
        }
        if (queryTime != null && !queryTime.trim().isEmpty()) {
            sql = sql + " and queryTime like'%" + queryTime + "%' ";
        }
        DataProcess data = DataProcess.getInstance();
        List<Accessories> list = null;
//                data.query(sql);
        System.out.println("query result size:" + list.size());
        return  list;
    }
}
