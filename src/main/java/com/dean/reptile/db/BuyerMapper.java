package com.dean.reptile.db;

import java.util.List;

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
    @Insert(
        {"insert into buyer(`jewelry_id`, `buyer_name`, `number`, `price`, `create_date`, `date`, `create_time`, "
            + "`update_time`, `status`, `buy_id`) "
            + "values(#{jewelryId}, #{buyerName}, #{number}, #{price}, #{createDate}, #{date}, #{createTime}, "
            + "#{updateTime}, #{status}, #{buyId})"})
    int insert(Buyer buyer);

    @Select("select * from buyer where jewelry_id=#{jewelryId} and buy_id=#{buyId}")
    Buyer selectByIndex(@Param("jewelryId") Integer jewelryId, @Param("buyId") Long buyId);

    @Update(
        {"update buyer set number=#{number}, price=#{price}, update_time=#{updateTime}, date=#{date}, "
            + "status=#{status} where id=#{id}"})
    int updateLastPrice(Buyer buyer);

    @Select({"<script> select * from buyer "
        + "where 1=1 "
        + "and <if test='jewelryId!=null'> jewelry_id = #{jewelryId} </if> "
        + "<if test='status!=null'> and status = #{status} </if> "
        + "<if test='price!=null'> and price ${comparison} #{price} </if> "
        + "order by ${sortKey} ${sortBy} limit #{from}, #{offset}; </script>"})
    List<Buyer> queryByJewelryId(@Param("jewelryId") Integer jewelryId,
                                 @Param("status") Integer status, @Param("from") Integer from,
                                 @Param("offset") Integer offset,
                                 @Param("sortKey") String sortKey, @Param("sortBy") String sortBy,
                                 @Param("comparison") String comparison, @Param("price") Double price);

    @Select("select count(*) from buyer;")
    Integer count();

    @Select("select * from buyer order by id asc limit #{page}, #{count};")
    List<Buyer> queryAll(@Param("page") int page, @Param("count") int count);

    @Select("select * from buyer where id= #{id};")
    Buyer queryById(@Param("id") int id);

    @Select({"<script> select count(*) from buyer "
        + "where 1=1 "
        + "and <if test='jewelryId!=null'> jewelry_id = #{jewelryId} </if> "
        + "<if test='status!=null'> and status = #{status} </if> "
        + "<if test='price!=null'> and price ${comparison} #{price} </if> "
        + "; </script>"})
    Integer queryByJewelryIdCount(@Param("jewelryId") Integer jewelryId,
                                  @Param("status") Integer status,
                                  @Param("comparison") String comparison, @Param("price") Double price);
}
