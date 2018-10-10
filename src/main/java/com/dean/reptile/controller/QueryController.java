package com.dean.reptile.controller;

import com.dean.reptile.bean.Accessories;
import com.dean.reptile.service.QueryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Admin on 2017/8/16.
 */
@RequestMapping(value = "query")
@Controller
public class QueryController {

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "hello, friend; query url: query/query, param: name, hero, queryTime.Such as: ip/query/query?name=xx&hero=xx";
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<Accessories> query(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "hero", required = false) String hero,
            @RequestParam(value = "queryTime", required = false) String queryTime
    ) {
//        return QueryService.instance().query(name, hero, queryTime);
        return null;
    }

}
