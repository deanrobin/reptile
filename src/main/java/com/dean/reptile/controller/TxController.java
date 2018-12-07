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
        @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
        @RequestParam(value = "offset", required = false, defaultValue = Integer.MAX_VALUE + "") Integer offset) {
        return txService.queryAll(from, offset);
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public Transaction queryById(
        @RequestParam(value = "id") Integer id) {
        return txService.queryById(id);
    }

    @RequestMapping("/query")
    @ResponseBody
    public Transaction query(
        @RequestParam(value = "jewelryId") Integer jewelryId,
        @RequestParam(value = "status", required = false) Integer status) {
        return txService.queryById(id);
    }
}
