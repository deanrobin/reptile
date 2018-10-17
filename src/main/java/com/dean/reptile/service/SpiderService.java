package com.dean.reptile.service;

import com.dean.reptile.mail.Email;
import org.springframework.beans.factory.annotation.Autowired;

public class SpiderService {

    @Autowired
    public Email email;

    protected static final String BASE_URL = "https://www.c5game.com/dota/";

    protected static final String END = ".html";
}
