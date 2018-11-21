package com.dean.reptile.constant;

import java.util.HashSet;
import java.util.Set;

public class HeroCache {

    private static Set<String> HERO_SET = null;

    public static Set<String> getHeroSet() {
        return HERO_SET;
    }

    public static void setHeroSet(Set<String> set) {
        HERO_SET = set;
    }
}
