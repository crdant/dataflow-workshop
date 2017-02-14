package io.pivotal.pa.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerSample {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerSample.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        kafkaTemplate.send("timer", dateFormat.format(new Date()));
    }

}
