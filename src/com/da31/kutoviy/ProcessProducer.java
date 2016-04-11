package com.da31.kutoviy;

public class ProcessProducer extends Thread {
    Queue queue1;
    Queue queue2;

    public ProcessProducer(Queue queue1, Queue queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    public synchronized void run() {
        while (!isInterrupted()) {
            pushToTheRightQueue(queue1, queue2, new Product(5));
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }

    public synchronized void pushToTheRightQueue(Queue queue1, Queue queue2, Product product) {
        if (queue1.getSize() > queue2.getSize()) {
            System.out.println("Queue2 push");
            product.numOfQueue = "2";
            queue2.push(product);
        }else if (queue1.getSize() < queue2.getSize()) {
            System.out.println("Queue1 push");
            product.numOfQueue = "1";
            queue1.push(product);
        }else{
            System.out.println("Queue1 push");
            product.numOfQueue = "1";
            queue1.push(product);
        }
    }
}
