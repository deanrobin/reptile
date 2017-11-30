package com.dean.reptile.controller;

import com.dean.reptile.Start;
import com.dean.reptile.task.QuartzClient;
import com.dean.reptile.util.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Admin on 2017/8/16.
 */
@RequestMapping(value = "init")
@Controller
public class InitController {
    private static long visitTime = 0L;
    private static long timeInterval = 1 * 60 * 60 * 1000L;
    private static boolean init = false;
//    @RequestMapping("/start")
//    @ResponseBody
//    public String start() {
//        long l = System.currentTimeMillis();
//        if (l - visitTime < timeInterval) {
//            return "you need wait for about:" + (l - visitTime) / (1000 * 60) + " minute";
//        }
//        visitTime = l;
//        Start start = new Start();
//        new Thread(start).start();
//        return "Get data has started";
//    }

    @RequestMapping("/init")
    @ResponseBody
    public int init(
            @RequestParam(value = "seconds", required = false) Integer seconds) {
        if (!init) {
            return 0;
        }
        init = true;
        QuartzClient quartzClient = QuartzClient.instance();
        quartzClient.init(seconds);
        return 1;
    }

    @RequestMapping("/begin")
    @ResponseBody
    public String begin() {
        long l = System.currentTimeMillis();
        if (l - visitTime < timeInterval) {
            return "you need wait for about:" + (l - visitTime) / (1000 * 60) + " minute";
        }
        visitTime = l;
        QuartzClient quartzClient = QuartzClient.instance();
        quartzClient.addJob();
        return "Get data has started";
    }

    @RequestMapping("/getBuy")
    @ResponseBody
    public String getBuy() {
        long l = System.currentTimeMillis();
        if (l - visitTime < timeInterval) {
            return "you need wait for about:" + (l - visitTime) / (1000 * 60) + " minute";
        }
        visitTime = l;
        QuartzClient quartzClient = QuartzClient.instance();
        quartzClient.getPurchaseIntent();
        return "Get buy data has started";
    }

    @RequestMapping("/getNGA")
    @ResponseBody
    public String getNGA(
            @RequestParam(value = "continue", required = false) Boolean resume
    ) {
        long l = System.currentTimeMillis();
        if (l - visitTime < timeInterval) {
            return "you need wait for about:" + (l - visitTime) / (1000 * 60) + " minute";
        }
        visitTime = l;
        if (resume != null) {
            Config.instance().setJobSearchContinue(resume);
        }
        QuartzClient quartzClient = QuartzClient.instance();
        quartzClient.getNGA();
        return "Get nga";
    }

}
