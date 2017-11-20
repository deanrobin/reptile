package com.dean.reptile.db;

import com.dean.reptile.bean.Accessories;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import com.dean.reptile.bean.Transaction;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configurable
public interface TransactionMapper {

    @Select("select * from transaction where accessories_id=#{accessoriesId} and transaction_time=#{transactionTime} and transaction_price=#{transactionPrice} and transaction_name=#{transactionName}")
    List<Transaction> findAll(Transaction transaction);

    @Options(useGeneratedKeys = true)
    @Insert("insert into transaction(`hero`, `accessories_id`, `transaction_time`, `transaction_price`, `transaction_name`, `transaction_number`) values(#{hero}, #{accessoriesId}, #{transactionTime}, #{transactionPrice}, #{transactionName}, #{transactionNumber})")
    int insert(Transaction transaction);

}
