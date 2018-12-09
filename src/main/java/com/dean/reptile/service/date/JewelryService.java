package com.dean.reptile.service.date;

import com.dean.reptile.bean.Transaction;
import com.dean.reptile.bean.ex.JewelryEx;
import com.dean.reptile.cache.TxCache;
import com.dean.reptile.db.JewelryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JewelryService {
    private static Logger log = LoggerFactory.getLogger(JewelryService.class);

    @Autowired
    JewelryMapper jewelryMapper;

    public Integer jewelryCount() {
        return jewelryMapper.getCount();
    }

    public List<JewelryEx> queryAll(Integer from, Integer offset) {
        return jewelryMapper.queryAll(from, offset);
    }

    //public Integer queryCount() {
    //
    //}
    //
    public List<JewelryEx> query(Integer from, Integer offset) {
        String sortKey = "id";
        String sortDesc = "asc";
        if (from == null) {
            from = 0;
        }
        if (offset == null) {
            offset = Integer.MAX_VALUE;
        }
        return jewelryMapper.query(null, sortKey, sortDesc, from, offset);
    }

    // 暂时匹配前10个
    public List<JewelryEx> searchByName(String name) {
        return jewelryMapper.searchByName(name, 0, 10);
    }

    public List<JewelryEx> searchByHero(String hero) {
        return jewelryMapper.searchByHero(hero, 0, 10);
    }


    public boolean updateNeed(int id, boolean need) {
        return jewelryMapper.updateNeed(id, need) == 1;
    }

    public void noticeNewTx() {
        log.warn("noticeNewTx..");
        List<Transaction> transactions = TxCache.instance.read();
        log.warn("transactions list size:" + transactions.size());

        if (transactions.size() == 0) {
            return;
        }
    }
}
