package com.dean.reptile.db;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.dean.reptile.bean.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMapper {


    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert({"insert into transaction(`jewelry_id`, `seller_name`, `final_price`, `timestamp`, `time_string`, `status`, create_time) "
        + "values(#{jewelryId}, #{sellerName}, #{finalPrice}, #{timestamp}, #{timeString}, #{status}, #{createTime})"})
    int insert(Transaction transaction);

    @Select("select * from transaction where jewelry_id=#{jewelryId} and seller_name=#{sellerName} and timestamp=#{timestamp}")
    Transaction querySameData(Transaction transaction);

    @Select("select count(*) from transaction;")
    Integer count();

    @Select("select * from transaction order by id asc limit #{page}, #{count};")
    List<Transaction> queryAll(@Param("page") int page, @Param("count") int count);

    @Select("select * from transaction where id= #{id};")
    Transaction queryById(@Param("id") int id);

    @Select({"<script> select * from transaction "
        + "where 1=1 "
        + "and <if test='jewelryId!=null'> jewelry_id = #{jewelryId} </if> "
        + "<if test='status!=null'> and status = #{status} </if> "
        + "order by transaction.id asc limit #{from}, #{offset}; </script>"})
    List<Transaction> queryByJewelryId(@Param("jewelryId") Integer jewelryId,
                            @Param("status") Integer status, @Param("from") Integer from, @Param("offset") Integer offset);

}
