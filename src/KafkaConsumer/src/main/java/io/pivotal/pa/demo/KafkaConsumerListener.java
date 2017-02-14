package io.pivotal.pa.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class KafkaConsumerListener {

    @KafkaListener(id = "fortran", topics = "timer", group = "group1")
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println(record);
    }
}
