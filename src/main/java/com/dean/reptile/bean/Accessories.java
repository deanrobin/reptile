package com.dean.reptile.bean;

/**
 * Created by Admin on 2017/8/16.
 */
public class Accessories {
    private int id;
    private String name;
    private String hero;
    private double price;
    private String queryTime;
    private double dealPrice;
    private String dealTime;
    private int huzuId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getHero() {
        return hero;
    }

    public double getPrice() {
        return price;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public void setDealPrice(double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public int getHuzuId() {
        return huzuId;
    }

    public void setHuzuId(int huzuId) {
        this.huzuId = huzuId;
    }
}
