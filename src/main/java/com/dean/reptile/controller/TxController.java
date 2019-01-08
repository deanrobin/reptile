package com.dean.reptile.controller;

import java.util.List;

import com.dean.reptile.bean.Transaction;
import com.dean.reptile.service.date.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dean
 */
@RequestMapping(value = "tx")
@Controller
public class TxController {
    @Autowired
    TxService txService;

    @RequestMapping("/count")
    @ResponseBody
    public Integer count() {
        return txService.count();
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Transaction> queryAll(
        @RequestParam(value = "from") Integer from,
        @RequestParam(value = "offset") Integer offset) {
        return txService.queryAll(from, offset);
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public Transaction queryById(
        @RequestParam(value = "id") Integer id) {
        return txService.queryById(id);
    }

    @RequestMapping("/queryByJewelry")
    @ResponseBody
    public List<Transaction> queryByJewelry(
            @RequestParam(value = "jewelryId") Integer jewelryId,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "from") Integer from,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "sortKey", required = false, defaultValue = "timestamp") String sortKey,
            @RequestParam(value = "sortBy", required = false, defaultValue = "desc") String sortBy) {
        return txService.queryByJewelry(jewelryId, status, from, offset, sortKey, sortBy);
    }
}
