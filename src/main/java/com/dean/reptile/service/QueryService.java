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

}
