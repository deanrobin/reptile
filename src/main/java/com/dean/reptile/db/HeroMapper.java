package com.dean.reptile.db;

import com.dean.reptile.bean.Hero;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HeroMapper {

    @Select("select * from hero")
    List<Hero> findAll();

    @Options(useGeneratedKeys = true)
    @Insert("insert into hero(name) values(#{name})")
    int insert(Hero heros);
}
