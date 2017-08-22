package com.dean.reptile.controller;

import com.dean.reptile.Start;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Admin on 2017/8/16.
 */
@RequestMapping(value = "init")
@Controller
public class InitController {
    private static long visitTime = 0L;
    private static long timeInterval = 1 * 60 * 60 * 1000L;

    @RequestMapping("/start")
    @ResponseBody
    public String start() {
        long l = System.currentTimeMillis();
        if (l - visitTime < timeInterval) {
            return "you need wait for about:" + (l - visitTime) / (1000 * 60) + " minute";
        }
        visitTime = l;
        Start start = new Start();
        new Thread(start).start();
        return "Get data has started";
    }
}
