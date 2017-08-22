package com.dean.reptile;

import com.dean.reptile.bean.Accessories;
import com.dean.reptile.service.Reptile;
import com.dean.reptile.util.DataProcess;

/**
 * Created by Admin on 2017/8/15.
 */

public class Start implements Runnable{
    public static int MaxNumber = 5500;
    public static int TimeInterval = 60; //单位分钟
    public static int startNumber = 1;

    private static void init() {
        for (int i = startNumber; i < 5500; i ++) {
            Reptile re = new Reptile();
            DataProcess ins = DataProcess.getInstance();
            String str = re.getHtml(i);
            if (str == null) {
                //如果是非200页面
                ins.insert404(i);
                continue;
            }
            Accessories a = re.parseHtml(str, i);
            if (a == null) {
                //如果发生了错误
                ins.insertError(i);
                continue;
            }

            ins.insert(a);
        }

    }

    private static void initConfig() {

    }

    @Override
    public void run() {
        System.out.println("go");
        init();
    }
}
