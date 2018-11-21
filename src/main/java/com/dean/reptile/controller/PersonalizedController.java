package com.dean.reptile.controller;

import com.dean.reptile.service.PersonalizedService;
import com.dean.reptile.task.QuartzClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "personalize")
@Controller
public class PersonalizedController {
    @Autowired
    private PersonalizedService personalizedService;

    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    @ResponseBody
    public String begin(
            @RequestParam(value = "hero", required = true) String hero,
            @RequestParam(value = "accessories", required = true) String accessories,
            @RequestParam(value = "isSubscribe", required = true) boolean isSubscribe) {
        personalizedService.updateSubscribe(hero, accessories, isSubscribe);
        return null;
    }
}
