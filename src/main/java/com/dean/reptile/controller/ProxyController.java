package com.dean.reptile.controller;

import java.util.Map;

import com.dean.reptile.util.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dean
 */
@Controller
@RequestMapping(value = "admin/proxy")
public class ProxyController {

    @RequestMapping("/getAll")
    @ResponseBody
    public Map<String, String> getAllProxy() {
        return HttpClient.map;
    }
}
