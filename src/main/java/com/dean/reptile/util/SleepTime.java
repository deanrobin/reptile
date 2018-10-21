package com.dean.reptile.util;

import java.util.Random;

public class SleepTime {

    public static int randomTime() {
        return new Random().nextInt(3000 - 1000) + 500;
    }
}
