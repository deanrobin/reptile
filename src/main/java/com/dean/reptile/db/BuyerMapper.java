package com.dean.reptile.db;

import com.dean.reptile.bean.Buyer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerMapper {

    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert({"insert into buyer(`jewelry_id`, `buyer_name`, `number`, `price`, `date`, `create_time`, `update_time`, `status`) "
        + "values(#{jewelryId}, #{buyerName}, #{number}, #{price}, #{date}, #{createTime}, #{updateTime}, #{status})"})
    int insert(Buyer buyer);
}
