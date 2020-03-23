package com.verizon.kafka;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaTestListener {
	
	private CountDownLatch latch = new CountDownLatch(3);

	  @KafkaListener(topics = "testTopic", 
			  groupId = "testGroupId", 
			  containerFactory = "kafkaListenerContainerFactory")
      public void listenGroupFoo(String message) {
          System.out.println("Received Messasge in group 'foo': " + message);
          latch.countDown();
      }
}
