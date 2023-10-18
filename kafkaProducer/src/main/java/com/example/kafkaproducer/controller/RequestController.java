package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.dto.RequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RequestController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/kafka/sum")
    public void sunRequest(@RequestBody RequestDTO requestDTO) throws JsonProcessingException, ExecutionException, InterruptedException {
        String json = objectMapper.writeValueAsString(requestDTO);
        SendResult<String, String> result = kafkaTemplate.send("kafkaSum", json).get();
        log.info(String.format("Numbers are sent to %s Offset %s Partition %s Topic",
                result.getRecordMetadata().offset(),
                result.getRecordMetadata().partition(),
                result.getRecordMetadata().topic()));
    }
}
