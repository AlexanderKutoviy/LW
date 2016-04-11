package com.da31.kutoviy;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue {
    List<Product> items = new LinkedList<Product>();
    public static int MAX_SIZE = 10;
    ReentrantLock lock = new ReentrantLock();
    Condition     emptyCondition = lock.newCondition();
    Condition     fullCondition = lock.newCondition();
    public /*synchronized*/ void push(Product item) {
        lock.tryLock();
        lock.lock();
        try {
            if (items.size() >= MAX_SIZE) {
                try {
                    fullCondition.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            items.add(item);
            emptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public /*synchronized*/ Product pull(){
        Product result = null;
        lock.lock();
        try{

            if( items.isEmpty() ){
                try {
                /*wait();*/
                    emptyCondition.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            }

            result =  items.isEmpty() ? null :items.remove(0);
            fullCondition.signal();
        }finally{
            lock.unlock();
        }
        return result;

    }
}
