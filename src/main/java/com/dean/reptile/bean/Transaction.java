package com.dean.reptile.bean;

/**
 * @author Dean
 */
public class Transaction {
    private int id;
    private int jewelryId;
    private String sellerName;
    private Double finalPrice;
    private Long timestamp;
    private String time_string;
    private String status;
    private Long createTime;

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

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTime_string() {
        return time_string;
    }

    public void setTime_string(String time_string) {
        this.time_string = time_string;
    }
}
