package com.da31.kutoviy;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue {

    private LinkedList<Product> items = new LinkedList<>();
    private int limit = 10;

    public synchronized void push(Product item) {
        while( items.size() >= limit ){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        items.add(item);
        notify();
    }

    public synchronized Product pull() {
        while( items.size() == 0 ){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Product temp = items.pop();
        notify();
        return temp;
    }

    public int getSize(){
        return items.size();
    }
}
