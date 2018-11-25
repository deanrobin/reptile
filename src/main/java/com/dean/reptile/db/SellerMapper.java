package com.dean.reptile.db;

import com.dean.reptile.bean.Seller;
import org.springframework.stereotype.Repository;

import com.dean.reptile.bean.Buyer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author dean
 */
@Repository
public interface SellerMapper {

    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert({"insert into seller(`jewelry_id`, `seller_name`, `number`, `price`, `create_date`, `date`, `create_time`, `update_time`, `status`, `sell_id`, `seller_level`, `seller_success_rate`, `seller_avg_time`) "
            + "values(#{jewelryId}, #{sellerName}, #{number}, #{price}, #{createDate}, #{date}, #{createTime}, #{updateTime}, #{status}, #{sellId}, #{sellerLevel}, #{sellerSuccessRate}, #{sellerAvgTime})"})
    int insert(Seller seller);

    @Select("select * from seller where jewelry_id=#{jewelryId} and sell_id=#{sellId}")
    Seller selectByIndex(@Param("jewelryId") Integer jewelryId, @Param("sellId") Long sellId);

    @Update({"update seller set price=#{price}, update_time=#{updateTime}, date=#{date}, status=#{status} where id=#{id}"})
    int updateLastPrice(Seller Seller);
}

