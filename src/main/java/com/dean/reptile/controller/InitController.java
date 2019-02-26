package com.dean.reptile.controller;

import com.dean.reptile.bean.response.ResponseBean;
import com.dean.reptile.constant.JobEnum;
import com.dean.reptile.constant.ResponseStatus;
import com.dean.reptile.constant.TimesCache;
import com.dean.reptile.service.impl.C5JewelrySpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Admin on 2017/8/16.
 */
@RequestMapping(value = "init")
@Controller
public class InitController {
    private static long visitTime = 0L;
    private static long timeInterval = 1 * 60 * 60 * 1000L;
    private static boolean init = false;

    @Autowired
    C5JewelrySpider c5JewelrySpider;

    /**
     * 更新饰品名字列表
     * @return
     */
    @RequestMapping("/updateList")
    @ResponseBody
    public ResponseBean getAllJewelry() {
        ResponseBean responseBean = new ResponseBean();
        long now = System.currentTimeMillis();
        if (now - TimesCache.getTime(JobEnum.UPDATE_JEWELRY_LIST) < timeInterval) {
            responseBean.setMessage("you need wait for about:" + (now - visitTime) / (1000 * 60) + " minute");
            responseBean.setStatus(ResponseStatus.TIME_TOO_SHORT.getstatus());
            return responseBean;
        }

        visitTime = now;
//        QuartzClient quartzClient = QuartzClient.instance();
//        quartzClient.addJob();
        return ResponseBean.getSuccess();
    }

    //@RequestMapping("/getBuy")
    //@ResponseBody
    //public String getBuy() {
    //    long l = System.currentTimeMillis();
    //    if (l - visitTime < timeInterval) {
    //        return "you need wait for about:" + (l - visitTime) / (1000 * 60) + " minute";
    //    }
    //    visitTime = l;
    //    QuartzClient quartzClient = QuartzClient.instance();
    //    quartzClient.getPurchaseIntent();
    //    return "Get buy data has started";
    //}

    /**
     * 更新饰品名字列表
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public ResponseBean test() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setMessage("this is your test!");
        responseBean.setStatus(ResponseStatus.SUCCESS.getstatus());
        return responseBean;
    }

}
