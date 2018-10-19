package com.dean.reptile.db;

import com.dean.reptile.bean.Jewelry;
import com.dean.reptile.bean.JewelryStatus;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JewelryMapper {

    @Insert("INSERT INTO jewelry(`name`, hero_name, last_price, create_time, last_time, html, hid, quality, rarity, `status`, indicative_price, source, type) " +
            "VALUES(#{name}, #{heroName}, #{lastPrice}, #{createTime}, #{lastTime}, #{html}, #{hid}, #{quality}, #{rarity}, #{status}, #{indicativePrice}, #{source}, #{type})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert( Jewelry jewelry);

    // 有可能返回null
    @Select("select * from jewelry where name=#{name} and hero_name=#{heroName}")
    Jewelry selectByIndex(@Param("name") String name, @Param("heroName") String heroName);

    @Update("update jewelry set last_price=#{lastPrice}, last_time=#{lastTime} where name=#{name} and hero_name=#{heroName}")
    int updateLastPrice(Jewelry jewelry);

    //其他值 默认 0 or false
    @Insert("INSERT INTO jewelry_status(id, `name`) VALUES(#{id}, #{name})")
    int insertJewelryStatus(@Param("id") int id, @Param("name") String name);

    @Select("select * from jewelry_status where id=#{id}")
    JewelryStatus selectJewelryStatusById(int id);

    @Select("select * from jewelry where id=#{id}")
    Jewelry selectJewelryById(int id);

    @Select("select * from jewelry_status where crawl_history = true")
    List<JewelryStatus> getNeedHistory();
}
