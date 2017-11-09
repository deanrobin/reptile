package com.dean.reptile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Admin on 2017/8/15.
 */
@SpringBootApplication
@MapperScan("com.dean.reptile")
public class Main {

    /**
     * Spring Boot 应用入口.
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
