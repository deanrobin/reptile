package com.dean.reptile.bean.ex;

import com.dean.reptile.bean.Jewelry;

public class JewelryEx extends Jewelry{

    // 爬取成交历史
    private boolean crawlHistory;
    // 爬取求购
    private boolean crawlBuy;
    // 是否通知有新的求购
    private boolean noticeBuy;
    // 爬取出售
    private boolean crawlSell;
    // 通知是否有新的出售
    private boolean noticeSell;
    // 求购价格线
    private Double buyPrice;
    // 出售价格线
    private Double sellPrice;

    public boolean isCrawlHistory() {
        return crawlHistory;
    }

    public void setCrawlHistory(boolean crawlHistory) {
        this.crawlHistory = crawlHistory;
    }

    public boolean isCrawlBuy() {
        return crawlBuy;
    }

    public void setCrawlBuy(boolean crawlBuy) {
        this.crawlBuy = crawlBuy;
    }

    public boolean isNoticeBuy() {
        return noticeBuy;
    }

    public void setNoticeBuy(boolean noticeBuy) {
        this.noticeBuy = noticeBuy;
    }

    public boolean isCrawlSell() {
        return crawlSell;
    }

    public void setCrawlSell(boolean crawlSell) {
        this.crawlSell = crawlSell;
    }

    public boolean isNoticeSell() {
        return noticeSell;
    }

    public void setNoticeSell(boolean noticeSell) {
        this.noticeSell = noticeSell;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
