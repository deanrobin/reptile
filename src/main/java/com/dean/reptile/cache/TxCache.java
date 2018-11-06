package com.dean.reptile.cache;

import com.dean.reptile.bean.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TxCache {

    private TxCache(){

    }
    public static TxCache instance = new TxCache();
    private static List<Transaction> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();


    public void add(Transaction transaction) {
        lock.writeLock().lock();
        list.add(transaction);
        lock.writeLock().unlock();
    }

    public List<Transaction> read() {
        List<Transaction> tx = new ArrayList<>();
        lock.writeLock().lock();
        tx.addAll(list);
        list.removeAll(list);
        lock.writeLock().unlock();
        return tx;
    }
}
