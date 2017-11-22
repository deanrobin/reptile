package com.dean.reptile.db;

import com.dean.reptile.bean.Accessories;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configurable
public interface AccessoriesMapper {

    @Select("select * from accessories where accessories=#{accessories} and hero=#{hero}")
    List<Accessories> findAll(Accessories accessories);

    @Options(useGeneratedKeys = true)
    @Insert("insert into Accessories(`hero`, `accessories`, `create_time`, `last_updated`, `price`, `source`, `url`, `subscribe`) values(#{hero}, #{accessories}, #{createTime}, #{lastUpdated}, #{price}, #{source}, #{url}, #{subscribe})")
    int insert(Accessories Accessories);

    @Update("update accessories set subscribe=#{subscribe} where hero=#{hero} and accessories=#{accessories}")
    int updateSubscribe(
            @Param(value="subscribe") boolean subscribe,
            @Param(value="hero") String hero,
            @Param(value="accessories")String accessories);

    @Update("update accessories set last_updated=#{timestamp}, price=#{price} and id=#{id}")
    int updatePrice(
            @Param(value="id") int id,
            @Param(value="timestamp") long timestamp,
            @Param(value="price") double price);

    @Select("select * from accessories where subscribe = 1")
    List<Accessories> findSubcribe();
}
