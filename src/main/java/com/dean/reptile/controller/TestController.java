package com.dean.reptile.controller;


import com.dean.reptile.bean.Hero;
import com.dean.reptile.db.HeroMapper;
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

    @RequestMapping("/index")
    @ResponseBody
    public String index() {

        return "hello, friend; query url: query/query, param: name, hero, queryTime.Such as: ip/query/query?name=xx&hero=xx";
    }
}
