package com.da31.kutoviy;

public class CpuConsumer extends Thread {
    Queue queue1;
    Queue queue2;

    public CpuConsumer(Queue queue1, Queue queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    public synchronized void run() {
        while (!isInterrupted()) {
            Product prod = getFromCurrentQueue(queue1,queue2);
            if (prod != null) {
                try {
                    System.out.println("Task is performing-->> " + prod.numOfQueue);
                    Thread.sleep(prod.time);
                } catch (InterruptedException ex) {
                    return;
                }
            } else {
                Thread.yield();
            }
        }
    }

    public Product getFromCurrentQueue(Queue queue1, Queue queue2) {
        if( queue1.getSize() != 0 || queue2.getSize() != 0) {
            if (queue1.getSize() > queue2.getSize()) {
                System.out.println("Queue1 get");
                return queue1.pull();
            } else if (queue1.getSize() < queue2.getSize()) {
                System.out.println("Queue2 get ");
                return queue2.pull();
            } else {
                System.out.println("Queue1 get");
                return queue1.pull();
            }
        }else{
            return null;
        }
    }
}
