package com.dean.reptile.service;

import com.dean.reptile.mail.Email;
import com.dean.reptile.util.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SpiderService {

    @Autowired
    public Email email;
    @Autowired
    protected HttpClient httpClient;

    protected static final String BASE_URL = "https://www.c5game.com/dota/";

    protected static final String C5_URL = "https://www.c5game.com";

    protected static final String END = ".html";

    public abstract String getEmailSubject();
}
