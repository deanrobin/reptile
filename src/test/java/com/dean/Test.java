package com.dean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

class ThreadPut extends Thread {
    private Object lock;

    public ThreadPut(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("thread put start");
        for (; Test.num <= 200; ++Test.num) {
            try {
                synchronized (lock) {
                    Test.arrayBlockingQueue.add(Test.num);
                    lock.notify();
                }
                System.out.println("queue add:" + Test.num + ", size:" + Test.arrayBlockingQueue.size());
            } catch (IllegalStateException ex) {
                System.out.println("queue 满了");
                Test.poll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadRead extends Thread {
    private Object lock;

    public ThreadRead(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        System.out.println("start 读取");
        System.out.println(this.getName());
        synchronized (lock) {
            while (Test.num != 180) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //业务
            Test.poll();

        }
    }
}

public class Test {

    public static ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue(100);
    public static int num = 1;
    private static int pollNum = 1;

    private static Object lock = new Object();

    public static void poll() {
        Thread thread = new Thread() {
            @Override
            public void start() {
                List<Integer> list = new ArrayList<>(100);
                synchronized (arrayBlockingQueue) {

                    int count = arrayBlockingQueue.size();
                    for (int i = 0; i < count; ++i) {
                        list.add(arrayBlockingQueue.poll());
                    }
                }
                System.out.println("arrayList size:" + list.size());
                System.out.println("poll number:" + pollNum);
                pollNum ++;
            }
        };
        thread.setName("a");
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        ThreadRead threadRead = new ThreadRead(obj);
        threadRead.start();
        threadRead.setName("abc");

        Thread.sleep(1000L);
        ThreadPut threadPut = new ThreadPut(obj);
        threadPut.start();

        System.out.println("over");
    }
}
