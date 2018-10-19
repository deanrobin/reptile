package com.dean.reptile.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import com.dean.reptile.bean.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionMapper {


    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert("insert into transaction(``, `accessories_id`, `transaction_time`, `transaction_price`, `transaction_name`, `transaction_number`) values(#{hero}, #{accessoriesId}, #{transactionTime}, #{transactionPrice}, #{transactionName}, #{transactionNumber})")
    int insert(Transaction transaction);

}
