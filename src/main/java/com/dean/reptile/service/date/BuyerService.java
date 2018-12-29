package com.dean.reptile.service.date;

import java.util.List;

import com.dean.reptile.bean.Buyer;
import com.dean.reptile.db.BuyerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dean
 */
@Service
public class BuyerService {
    @Autowired
    BuyerMapper buyerMapper;

    public List<Buyer> queryByJewelry(Integer jewelryId, Integer status, Integer from,
                                      Integer offset, String sortKey, String sortBy,
                                        String comparison, Double price) {
        return buyerMapper.queryByJewelryId(jewelryId, status,
            from, offset, sortKey, sortBy, comparison, price);
    }

    public Integer count() {
        return buyerMapper.count();
    }

    public List<Buyer> queryAll(Integer from, Integer offset) {
        return buyerMapper.queryAll(from, offset);
    }

    public Buyer queryById(Integer id) {
        return buyerMapper.queryById(id);
    }

    public Integer queryByJewelryCount(Integer jewelryId, Integer status, String comparison, Double price) {
        return buyerMapper.queryByJewelryIdCount(jewelryId, status, comparison, price);
    }
}
