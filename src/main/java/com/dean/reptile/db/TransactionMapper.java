package com.dean.reptile.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
    //@InsertProvider(type = Transaction.class, method = "saveAll")
    //void saveAll(@Param("list") List<Transaction> transactions);
}
