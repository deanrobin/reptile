package com.dean.reptile.bean;

/**
 * @author dean
 */
public class Seller {
    private int id;
    private int jewelryId;
    private String sellerName;
    private Double price;
    private Long date;
    private Long createDate;
    private Long createTime;
    private Long updateTime;
    private Integer status;
    private Integer number;
    private Long sellId;
    private Integer sellerLevel;
    private String sellerSuccessRate;
    private String sellerAvgTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(int jewelryId) {
        this.jewelryId = jewelryId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getSellId() {
        return sellId;
    }

    public void setSellId(Long sellId) {
        this.sellId = sellId;
    }

    public Integer getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(Integer sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public String getSellerSuccessRate() {
        return sellerSuccessRate;
    }

    public void setSellerSuccessRate(String sellerSuccessRate) {
        this.sellerSuccessRate = sellerSuccessRate;
    }

    public String getSellerAvgTime() {
        return sellerAvgTime;
    }

    public void setSellerAvgTime(String sellerAvgTime) {
        this.sellerAvgTime = sellerAvgTime;
    }
}
