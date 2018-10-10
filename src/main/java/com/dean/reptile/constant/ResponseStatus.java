package com.dean.reptile.constant;

public enum ResponseStatus {
    SUCCESS("success", 100),
    TIME_TOO_SHORT("request time too short", 1);

    // 成员变量
    private String name;
    private int status;

    private ResponseStatus(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getstatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }
}
