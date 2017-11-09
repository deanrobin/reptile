package com.dean.reptile.db;

import com.dean.reptile.bean.Hero;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HeroMapper {

    @Select("select * from hero")
    List<Hero> findAll();

    @Insert("insert into hero(name) values(#{name})")
    void insert(Hero heros);
}
