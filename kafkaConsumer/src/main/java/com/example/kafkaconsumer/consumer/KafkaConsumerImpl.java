package com.example.kafkaconsumer.consumer;

import com.example.kafkaconsumer.dto.RequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerImpl {

  ObjectMapper objectMapper = new ObjectMapper();
  private final KafkaTemplate<String, String> kafkaTemplate;


  @KafkaListener(topics = "kafkaSum", groupId = "Group100")
  public void listen(String name) throws JsonProcessingException {
    RequestDTO requestDTO = objectMapper.readValue(name, RequestDTO.class);
    //System.out.println("Received num 1: " + requestDTO.getNum1() + " and num 2:- " + requestDTO.getNum2() + " from topics");
    log.info("Received num 1: " + requestDTO.getNum1() + " and num 2:- " + requestDTO.getNum2() + " from topics");

    int sum=requestDTO.getNum2() + requestDTO.getNum1();
//    System.out.println("Sum is " + sum);
    log.info("Sum is " + sum);

    // Send the sum to another Kafka topic
    sendSumToKafkaTopic(sum);
  }

  private void sendSumToKafkaTopic(int sum) {
    try {
      String sumJson = objectMapper.writeValueAsString(sum);
      kafkaTemplate.send("sumResult", sumJson);
      log.info("Sent sum to resultTopic: " + sum);
    } catch (JsonProcessingException e) {
      log.error("Error sending sum to Kafka topic: " + e.getMessage());
    }
  }
}
