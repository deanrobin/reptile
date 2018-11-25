package com.dean.reptile.analyze;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.dean.reptile.bean.Buyer;
import com.dean.reptile.bean.Seller;
import com.dean.reptile.constant.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C5JSON {

    private static Logger log = LoggerFactory.getLogger(C5JSON.class);
    //private String doc = null;
    //
    //public C5JSON(String doc) {
    //    this.doc = doc;
    //}

    public static List<Buyer> getBuyers(String doc, Integer jewelryId) {
        List<Buyer> list = new ArrayList<>();

        try {
            JSONObject json = JSON.parseObject(doc);
            if (json.getIntValue("status") != 200) {
                log.info("this jewelryId get buyer status != 200");
                return null;
            }

            JSONArray array = json.getJSONObject("body").getJSONArray("items");
            int i = 0;
            for (; i < array.size(); ++i) {
                JSONObject obj = array.getJSONObject(i);
                Buyer buyer = new Buyer();
                buyer.setJewelryId(jewelryId);
                buyer.setPrice(obj.getDouble("price"));
                buyer.setBuyerName(obj.getJSONObject("owner").getString("nickname"));
                buyer.setNumber(obj.getInteger("need_num"));

                long now = System.currentTimeMillis();
                buyer.setCreateTime(now);
                buyer.setUpdateTime(now);
                buyer.setDate(Long.valueOf(obj.getString("update_time")));
                buyer.setCreateDate(Long.valueOf(obj.getString("create_time")));
                buyer.setBuyId(Long.valueOf(obj.getString("id")));
                buyer.setStatus(StatusEnum.SUCCESS.getCode());
                list.add(buyer);
            }

            return list;
        } catch (Exception e) {
            log.info("getBuyers error, jewelryId:" + jewelryId, e);
            return null;
        }
    }

    public static List<Seller> getSeller(String doc, Integer jewelryId) {
        List<Seller> list = new ArrayList<>();

        try {
            JSONObject json = JSON.parseObject(doc);
            if (json.getIntValue("status") != 200) {
                log.info("this jewelryId get seller status != 200");
                return null;
            }

            JSONArray array = json.getJSONObject("body").getJSONArray("items");
            int i = 0;
            for (; i < array.size(); ++i) {
                JSONObject obj = array.getJSONObject(i);

                Seller seller = new Seller();
                seller.setJewelryId(jewelryId);
                seller.setPrice(obj.getDouble("price"));
                seller.setSellerName(obj.getJSONObject("owner").getString("nickname"));
//                buyer.setNumber(obj.getInteger("need_num"));
                seller.setNumber(1);

                long now = System.currentTimeMillis();
                seller.setCreateTime(now);
                seller.setUpdateTime(now);
                seller.setDate(Long.valueOf(obj.getString("update_time")));
                seller.setCreateDate(Long.valueOf(obj.getString("create_time")));
                seller.setSellId(Long.valueOf(obj.getString("id")));

                seller.setSellerLevel(obj.getJSONObject("owner").getInteger("level"));
                seller.setSellerSuccessRate(obj.getJSONObject("owner").getString("success_rate"));
                seller.setSellerAvgTime(obj.getJSONObject("owner").getString("avg_time"));

                seller.setStatus(StatusEnum.SUCCESS.getCode());
                list.add(seller);
            }

            return list;
        } catch (Exception e) {
            log.info("getSeller error,jewelryId:" + jewelryId, e);
            return null;
        }
    }
}
