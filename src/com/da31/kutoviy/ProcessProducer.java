package com.da31.kutoviy;

public class ProcessProducer extends Thread {
    Queue queue;

    public ProcessProducer(Queue queue) {
        this.queue = queue;
    }

    public void run() {
        while (!isInterrupted()) {
            queue.push(new Product(5));
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}
