package com.dean.reptile.service.date;

import com.dean.reptile.bean.ex.JewelryEx;
import com.dean.reptile.db.JewelryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JewelryService {

    @Autowired
    JewelryMapper jewelryMapper;

    public Integer jewelryCount() {
        return jewelryMapper.getCount();
    }

    public List<JewelryEx> queryAll() {
        return jewelryMapper.query(0, Integer.MAX_VALUE);
    }

    public boolean updateNeed(int id, boolean need) {
        return jewelryMapper.updateNeed(id, need) == 1;
    }
}
