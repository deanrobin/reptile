package com.dean.reptile.constant;

public enum StatusEnum{
    UNFINISHED("未完成", 0),
    SUCCESS("成功" , 1);

    // 成员变量
    private String name;
    private int code;

    private StatusEnum(String name, int code) {
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
