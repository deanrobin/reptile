package com.dean.reptile.db;

import com.dean.reptile.bean.Buyer;
import com.dean.reptile.bean.own.Error;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorMapper {

    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert({"insert into error(`type`, `desc`) "
            + "values(#{type}, #{desc})"})
    int insert(Error error);

}
