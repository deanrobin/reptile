package com.dean.reptile.db;

import com.dean.reptile.bean.Hero;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HeroMapper {

    @Insert("insert into hero(name) values(#{name})")
    int insert(String name);

    // 有可能返回null
    @Select("select id from hero where name=#{name}")
    Integer selectByName(String name);

    // 有可能返回null
    @Select("select * from hero where name=#{name}")
    Hero select(String name);

    @Select("select name from hero")
    Set<String> selectAll();

    @Update("update hero set name=#{name} where id=#{id}")
    int update(@Param("id") int id, @Param("name") String name);


}
