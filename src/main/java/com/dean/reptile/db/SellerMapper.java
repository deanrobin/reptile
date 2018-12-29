package com.dean.reptile.db;

import java.util.List;

import com.dean.reptile.bean.Seller;
import org.springframework.stereotype.Repository;

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

    @Select({"<script> select * from seller "
        + "where 1=1 "
        + "and <if test='jewelryId!=null'> jewelry_id = #{jewelryId} </if> "
        + "<if test='status!=null'> and status = #{status} </if> "
        + "<if test='price!=null'> and price ${comparison} #{price} </if> "
        + "order by ${sortKey} ${sortBy} limit #{from}, #{offset}; </script>"})
    List<Seller> queryByJewelryId(@Param("jewelryId") Integer jewelryId,
                                 @Param("status") Integer status, @Param("from") Integer from,
                                 @Param("offset") Integer offset,
                                 @Param("sortKey") String sortKey, @Param("sortBy") String sortBy,
                                 @Param("comparison") String comparison, @Param("price") Double price);

    @Select("select count(*) from seller;")
    Integer count();

    @Select("select * from seller order by id asc limit #{page}, #{count};")
    List<Seller> queryAll(@Param("page") int page, @Param("count") int count);

    @Select("select * from seller where id= #{id};")
    Seller queryById(@Param("id") int id);

    @Select({"<script> select count(*) from seller "
        + "where 1=1 "
        + "and <if test='jewelryId!=null'> jewelry_id = #{jewelryId} </if> "
        + "<if test='status!=null'> and status = #{status} </if> "
        + "<if test='price!=null'> and price ${comparison} #{price} </if> "
        + "; </script>"})
    Integer queryByJewelryIdCount(@Param("jewelryId") Integer jewelryId,
                                  @Param("status") Integer status,
                                  @Param("comparison") String comparison, @Param("price") Double price);
}

