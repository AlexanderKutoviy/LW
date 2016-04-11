package com.da31.kutoviy;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Queue queue1 = new Queue();
        Queue queue2 = new Queue();

        CpuConsumer consumer = new CpuConsumer(queue1, queue2);
        CpuConsumer consumer2 = new CpuConsumer(queue1, queue2);
        ProcessProducer producer = new ProcessProducer(queue1,queue2);
        ProcessProducer producer1 = new ProcessProducer(queue1,queue2);
        producer.start();
        producer1.start();
        consumer.start();
        consumer2.start();

        Thread.sleep(100);
        consumer.interrupt();
        consumer2.interrupt();
        producer.interrupt();
        producer1.interrupt();

    }
}
