package com.dean;

import com.dean.reptile.constant.HeroCache;
import com.dean.reptile.db.HeroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Runner implements CommandLineRunner {
    private static Logger log = LoggerFactory.getLogger(Runner.class);

    @Autowired
    HeroMapper heroMapper;

    @Override
    public void run(String... args) throws Exception {
        Set<String> set = heroMapper.selectAll();
        HeroCache.setHeroSet(set);
        log.info("init Hero Set success...");
    }
}
