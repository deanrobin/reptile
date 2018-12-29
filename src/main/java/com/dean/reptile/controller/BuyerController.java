package com.dean.reptile.controller;

import java.util.List;

import com.dean.reptile.bean.Buyer;
import com.dean.reptile.bean.Transaction;
import com.dean.reptile.service.date.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dean
 */
@RequestMapping(value = "buy")
@Controller
public class BuyerController {
    @Autowired
    BuyerService buyerService;

    @RequestMapping("/count")
    @ResponseBody
    public Integer count() {
        return buyerService.count();
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Buyer> queryAll(
        @RequestParam(value = "from") Integer from,
        @RequestParam(value = "offset") Integer offset) {
        return buyerService.queryAll(from, offset);
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public Buyer queryById(
        @RequestParam(value = "id") Integer id) {
        return buyerService.queryById(id);
    }

    @RequestMapping("/queryByJewelry")
    @ResponseBody
    public List<Buyer> queryByJewelry(
        @RequestParam(value = "jewelryId") Integer jewelryId,
        @RequestParam(value = "status", required = false) Integer status,
        @RequestParam(value = "from") Integer from,
        @RequestParam(value = "offset") Integer offset,
        @RequestParam(value = "sortKey", required = false, defaultValue = "create_time") String sortKey,
        @RequestParam(value = "sortBy", required = false, defaultValue = "desc") String sortBy,
        @RequestParam(value = "comparison", required = false, defaultValue = ">=") String comparison,
        @RequestParam(value = "price", required = false, defaultValue = "0") Double price) {
        return buyerService.queryByJewelry(jewelryId, status, from, offset, sortKey, sortBy, comparison, price);
    }

    @RequestMapping("/queryByJewelryCount")
    @ResponseBody
    public Integer queryByJewelry(
        @RequestParam(value = "jewelryId") Integer jewelryId,
        @RequestParam(value = "status", required = false) Integer status,
        @RequestParam(value = "comparison", required = false, defaultValue = ">=") String comparison,
        @RequestParam(value = "price", required = false, defaultValue = "0") Double price) {
        return buyerService.queryByJewelryCount(jewelryId, status, comparison, price);
    }
}
