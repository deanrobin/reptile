package com.dean.reptile.constant;

public enum JobEnum {
    UPDATE_JEWELRY_LIST("更新饰品总列表", 1);

    // 成员变量
    private String name;
    private int index;

    private JobEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static Integer getIndex(JobEnum job) {
        for (JobEnum j : JobEnum.values()) {
            if (j.getIndex() == job.getIndex()) {
                return j.getIndex();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
