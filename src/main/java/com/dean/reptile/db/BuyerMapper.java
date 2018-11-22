package com.dean.reptile.db;

import com.dean.reptile.bean.Buyer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author dean
 */
@Repository
public interface BuyerMapper {

    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert({"insert into buyer(`jewelry_id`, `buyer_name`, `number`, `price`, `create_date`, `date`, `create_time`, `update_time`, `status`) "
        + "values(#{jewelryId}, #{buyerName}, #{number}, #{price}, #{createDate}, #{date}, #{createTime}, #{updateTime}, #{status})"})
    int insert(Buyer buyer);

    @Select("select * from buyer where jewelry_id=#{jewelryId} and buyer_name=#{buyerName} and create_date=#{createDate}")
    Buyer selectByIndex(@Param("jewelryId") Integer jewelryId, @Param("buyerName") String buyerName, @Param("createDate") Long createDate);

    @Update({"update buyer set number=#{number}, price=#{price}, update_time=#{updateTime}, date=#{date} where id=#{id}"})
    int updateLastPrice(Buyer buyer);
}
