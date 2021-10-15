package com.sfg.jms.sender;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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
		//		log.trace("Sending a message");
		//		System.out.println("Sending a message");

		HelloWorldMessage message = HelloWorldMessage.builder().id(UUID.randomUUID()).message("Hello Smart World").build();

		jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

		//		log.trace("Message sent.");
		//		System.out.println("Message sent");
	}


	@Scheduled(fixedRate = 2000)
	private void sendMessageOnSendReceiveQueue() {
		// @formatter:off
		HelloWorldMessage message = HelloWorldMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hello")
				.build(); 
// @formatter:on



		jmsTemplate.sendAndReceive(JmsConfig.MY_SND_RCV_QUEUE, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				Message helloMessage = null;
				helloMessage = session.createObjectMessage(message);
				//				 helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
				helloMessage.setStringProperty("_type", HelloWorldMessage.class.getName());

				System.out.println("Sending Hello");

				return helloMessage;
			}
		});
	}

}
