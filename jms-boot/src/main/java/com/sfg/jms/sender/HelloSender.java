package com.sfg.jms.sender;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sfg.jms.config.JmsConfig;
import com.sfg.jms.model.HelloWorldMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class HelloSender {

	private final JmsTemplate jmsTemplate;

	@Scheduled(fixedRate = 2000)
	private void sendMessage() {
		// TODO Auto-generated method stub
		log.trace("Sending a message");

		HelloWorldMessage message = HelloWorldMessage.builder().id(UUID.randomUUID()).message("Hello Smart World").build();

		jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

		log.trace("Message sent.");
	}



}
