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

/**
 * @author dean
 */
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
    public List<JewelryEx> queryAll(@RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                    @RequestParam(value = "offset", required = false, defaultValue = Integer.MAX_VALUE + "") Integer offset) {
        return jewelryService.queryAll(from, offset);
    }

    @RequestMapping(value = "/updateNeed", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateNeed(@RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "need", required = true) boolean need) {
        return jewelryService.updateNeed(id, need);
    }

    @RequestMapping("/queryCount")
    @ResponseBody
    public Integer queryCount() {
        return jewelryService.jewelryCount();
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<JewelryEx> query(@RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                    @RequestParam(value = "offset", required = false, defaultValue = Integer.MAX_VALUE + "") Integer offset) {
        return jewelryService.query();
    }

    @RequestMapping("/searchByName")
    @ResponseBody
    public List<JewelryEx> searchByName(@RequestParam(value = "name", required = true) String name) {
        return jewelryService.searchByName(name);
    }

    @RequestMapping("/searchByHero")
    @ResponseBody
    public List<JewelryEx> searchByHero(@RequestParam(value = "hero", required = true) String hero) {
        return jewelryService.searchByHero(hero);
    }




    //@RequestMapping("/crawlJewelryHistory")
    //@ResponseBody
    //@Deprecated
    //public ResponseBean crawlJewelryHistory() {
    //    new Runnable() {
    //        @Override
    //        public void run() {
    //            c5JewelrySpider.crawlHistory();
    //        }
    //    }.run();
    //
    //    return ResponseBean.getSuccess();
    //}
}
