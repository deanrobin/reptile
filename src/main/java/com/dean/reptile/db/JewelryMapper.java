package com.dean.reptile.db;

import com.dean.reptile.bean.Jewelry;
import com.dean.reptile.bean.JewelryStatus;
import com.dean.reptile.bean.ex.JewelryEx;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JewelryMapper {

    @Insert({"INSERT INTO jewelry(`name`, hero_name, last_price, create_time, last_time, html, "
        + "hid, quality, rarity, `status`, indicative_price, source, type, total_sales, total_week) " +
            "VALUES(#{name}, #{heroName}, #{lastPrice}, #{createTime}, #{lastTime}, #{html}, "
        + "#{hid}, #{quality}, #{rarity}, #{status}, #{indicativePrice}, #{source}, #{type}, #{totalSales}, #{totalWeek})"})
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert( Jewelry jewelry);

    // 有可能返回null
    @Select("select * from jewelry where name=#{name} and hero_name=#{heroName}")
    Jewelry selectByIndex(@Param("name") String name, @Param("heroName") String heroName);

    @Update({"update jewelry set last_price=#{lastPrice}, last_time=#{lastTime}, total_sales=#{totalSales}, total_week=#{totalWeek} where name=#{name} and hero_name=#{heroName}"})
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

    @Select("select count(*) from jewelry;")
    Integer getCount();

    @Select("select * from jewelry left join jewelry_status ON jewelry.id = jewelry_status.id order by jewelry.id asc limit #{page}, #{count};")
    List<JewelryEx> queryAll(@Param("page") int page, @Param("count") int count);

    @Update({"update jewelry_status set crawl_history=#{need} where id=#{id}"})
    int updateNeed(@Param("id") int id, @Param("need") boolean need);

    @Select("select * from jewelry left join jewelry_status ON jewelry.id = jewelry_status.id where crawl_buy = true")
    List<JewelryEx> getFetchBuy();

    @Select("select * from jewelry left join jewelry_status ON jewelry.id = jewelry_status.id where crawl_sell = true")
    List<JewelryEx> getFetchSell();

    @Select("select * from jewelry left join jewelry_status ON jewelry.id = jewelry_status.id where jewelry.`name` like '%${name}%' order by jewelry.id asc limit #{page}, #{count};")
    List<JewelryEx> searchByName(@Param("name") String name, @Param("page") int page, @Param("count") int count);

    @Select("select * from jewelry left join jewelry_status ON jewelry.id = jewelry_status.id where hero_name=#{hero} order by jewelry.id asc limit #{page}, #{count};")
    List<JewelryEx> searchByHero(@Param("hero") String hero, @Param("page") int page, @Param("count") int count);

    @Select("<script> select * from jewelry left join jewelry_status ON jewelry.id = jewelry_status.id "
        + "where 1=1 "
        + "and <if test='hero!=null'> hero = #{hero} </if> "
        + "order by jewelry.#{sortKey} #{sortDesc} limit #{page}, #{count}; </script>")
    List<JewelryEx> query(@Param("hero") String hero,
                          @Param("sortKey") String sortKey, @Param("sortDesc") String sortDesc,
                          @Param("page") int page, @Param("count") int count);

}
