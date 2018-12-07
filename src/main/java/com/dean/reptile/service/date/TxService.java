package com.dean.reptile.service.date;

import java.util.List;

import com.dean.reptile.bean.Transaction;
import com.dean.reptile.db.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dean
 */
@Service
public class TxService {
    @Autowired
    private TransactionMapper txMapper;

    public Integer count() {
        return txMapper.count();
    }

    public List<Transaction> queryAll(Integer from, Integer offset) {
        return txMapper.queryAll(from, offset);
    }

    public Transaction queryById(Integer id) {
        return txMapper.queryById(id);
    }
}
