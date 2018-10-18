package com.dean.reptile.db;

import com.dean.reptile.bean.Jewelry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface JewelryMapper {

    @Insert("INSERT INTO jewelry(`name`, hero_name, last_price, create_time, last_time, html, hid, quality, rarity, `status`, indicative_price, source, type) " +
            "VALUES(#{name}, #{heroName}, #{lastPrice}, #{createTime}, #{lastTime}, #{html}, #{hid}, #{quality}, #{rarity}, #{status}, #{indicativePrice}, #{source}, #{type})")
    int insert( Jewelry jewelry);

    // 有可能返回null
    @Select("select * from jewelry where name=#{name} and hero_name=#{heroName}")
    Jewelry selectByIndex(@Param("name") String name, @Param("heroName") String heroName);

    @Update("update jewelry set last_price=#{lastPrice}, last_time=#{lastTime} where name=#{name} and hero_name=#{heroName}")
    int updateLastPrice(Jewelry jewelry);
}
