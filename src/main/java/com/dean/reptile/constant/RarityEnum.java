package com.dean.reptile.constant;

import java.util.HashMap;
import java.util.Map;

public enum  RarityEnum {
    NORMAL("普通", 1),
    UNCOMMON("罕见", 2),
    RARE("稀有", 3),
    MYTH("神话", 4),
    IMMORTAL("不朽", 5),
    ANCIENT("远古", 6),
    LEGEND("传说", 7),
    TREASURE("至宝", 8);

    public static Map<String, Integer> map = new HashMap<>();

    // 成员变量
    private String name;
    private int id;

    private RarityEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // 启动时 放进map 直接get
    static {
        for (RarityEnum re : RarityEnum.values()) {
            map.put(re.getName(), re.getId());
        }
    }

    public static RarityEnum getById(Integer id) {
        for (RarityEnum re : RarityEnum.values()) {
            if (id.equals(re.getId())) {
                return re;
            }
        }
        return null;
    }

    public static RarityEnum getByName(String name) {
        return RarityEnum.valueOf(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
