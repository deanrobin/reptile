package com.dean.reptile.controller;

import com.dean.reptile.bean.ex.JewelryEx;
import com.dean.reptile.bean.response.ResponseBean;
import com.dean.reptile.service.date.JewelryService;
import com.dean.reptile.service.impl.C5JewelrySpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "jewelry")
@Controller
public class JewelryController {

    @Autowired
    C5JewelrySpider c5JewelrySpider;
    @Autowired
    JewelryService jewelryService;

    @RequestMapping("/count")
    @ResponseBody
    public Integer count() {
        return jewelryService.jewelryCount();
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<JewelryEx> queryAll() {
        return jewelryService.queryAll();
    }

    @RequestMapping(value = "/updateNeed", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateNeed(@RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "need", required = true) boolean need) {
        return jewelryService.updateNeed(id, need);
    }

    @RequestMapping("/crawlJewelryHistory")
    @ResponseBody
    public ResponseBean crawlJewelryHistory() {
        new Runnable() {
            @Override
            public void run() {
                c5JewelrySpider.crawlHistory();
            }
        }.run();

        return ResponseBean.getSuccess();
    }
}
