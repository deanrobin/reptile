package com.dean;

import com.dean.reptile.bean.Hero;
import com.dean.reptile.constant.HeroCache;
import com.dean.reptile.db.HeroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    HeroMapper heroMapper;

    @Override
    public void run(String... args) throws Exception {
        Set<String> set = heroMapper.selectAll();
        HeroCache.setHeroSet(set);
        System.out.println("init Hero Set success...");
    }
}
