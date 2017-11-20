package com.dean.reptile.db;

import com.dean.reptile.bean.Accessories;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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
}
