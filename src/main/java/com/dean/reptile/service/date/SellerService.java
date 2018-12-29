package com.dean.reptile.service.date;

import java.util.List;

import com.dean.reptile.bean.Seller;
import com.dean.reptile.db.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dean
 */
@Service
public class SellerService {
    @Autowired
    SellerMapper sellerMapper;

    public List<Seller> queryByJewelry(Integer jewelryId, Integer status, Integer from,
                                       Integer offset, String sortKey, String sortBy,
                                       String comparison, Double price) {
        return sellerMapper.queryByJewelryId(jewelryId, status,
            from, offset, sortKey, sortBy, comparison, price);
    }

    public Integer count() {
        return sellerMapper.count();
    }

    public List<Seller> queryAll(Integer from, Integer offset) {
        return sellerMapper.queryAll(from, offset);
    }

    public Seller queryById(Integer id) {
        return sellerMapper.queryById(id);
    }

    public Integer queryByJewelryCount(Integer jewelryId, Integer status, String comparison, Double price) {
        return sellerMapper.queryByJewelryIdCount(jewelryId, status, comparison, price);
    }
}
