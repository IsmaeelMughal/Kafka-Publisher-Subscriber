package com.example.kafkaproducer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SumListener {

    @KafkaListener(topics = "sumResult", groupId = "Group101")
    public void listen(String sum){
        log.info("Received SUM to producer is " + sum);
    }
}
