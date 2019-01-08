package com.dean.reptile.constant;

/**
 * @author dean
 */

public enum  ErrorTypeEnum {

    FETCH_BUY_ERROR("抓取求购出错了", 10001),
    FETCH_SELL_ERROR("抓取出售出错了" , 10002);

    // 成员变量
    private String name;
    private int code;

    private ErrorTypeEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
