package com.dean.reptile.task;

import org.quartz.Job;

public abstract class C5BaseTask implements Job {

    protected static final String BASE_URL = "https://www.c5game.com/dota/";

    protected static final String END = ".html";
}
