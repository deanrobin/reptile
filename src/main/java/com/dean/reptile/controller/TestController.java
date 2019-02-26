package com.dean.reptile.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.dean.reptile.bean.Hero;
import com.dean.reptile.db.HeroMapper;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.apache.coyote.http2.Http2Error;
import org.apache.coyote.http2.Http2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private HeroMapper heroMapper;

//    @RequestMapping(value = "/add" , method = RequestMethod.GET)
//    @ResponseBody
//    public Hero addHero(@RequestParam String name) {
//        Hero hero = new Hero();
//        hero.setName(name);
//        int i = heroMapper.insert(hero);
//        int x = hero.getId();
//        return hero;
//    }

    @RequestMapping(value = "/index" , method = RequestMethod.POST)
    @ResponseBody
    public String index(@RequestBody String name) {
        System.out.println(name);
        JSONObject param = new JSONObject();
        param.put("key", "tm");
        JSONObject obj = new JSONObject();
        obj.put("from", "dean");
        obj.put("age", 2);
        obj.put("param", param);
        JSONArray array = new JSONArray();
        array.add(param);
        array.add("12312312");
        obj.put("array", array);
        return obj.toJSONString();
    }

    @RequestMapping(value = "/index2" , method = RequestMethod.GET)
    @ResponseBody
    public String index2(@RequestParam String name,
                         @RequestParam String age) {

        return "hello, friend; query me " + name + age;
    }

    @RequestMapping(value = "/index3" , method = RequestMethod.POST)
    @ResponseBody
    public String index3(@RequestBody String name) {

        throw new InternalException("sdfsfds");
    }
}
