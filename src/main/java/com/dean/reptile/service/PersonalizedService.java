package com.dean.reptile.service;

import com.dean.reptile.db.AccessoriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalizedService {
    @Autowired
    private AccessoriesMapper accessoriesMapper;

    public int updateSubscribe(String hero, String accessories, boolean subscribe) {
        int i = accessoriesMapper.updateSubscribe(subscribe, hero, accessories);
        return i;
    }

}
