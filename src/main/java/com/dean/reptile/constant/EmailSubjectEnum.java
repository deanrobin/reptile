package com.dean.reptile.constant;

public enum EmailSubjectEnum {
    UPDATE_JEWELRY_LIST("爬虫更新全饰品信息", 1);

    // 成员变量
    private String subject;
    private int index;

    private EmailSubjectEnum(String subject, int index) {
        this.subject = subject;
        this.index = index;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
