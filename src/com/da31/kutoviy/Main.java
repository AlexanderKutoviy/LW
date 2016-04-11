package com.da31.kutoviy;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Queue q = new Queue();


        CpuConsumer consumer = new CpuConsumer(q);
        ProcessProducer producer = new ProcessProducer(q);
        consumer.start();
        producer.start();

        //ProcessProducer producer = new ProcessProducer(q);
        CpuConsumer consumer2 = new CpuConsumer(q);
        consumer2.start();


        Thread.sleep(15000);
        consumer.interrupt();
        consumer2.interrupt();
        producer.interrupt();
    }
}
