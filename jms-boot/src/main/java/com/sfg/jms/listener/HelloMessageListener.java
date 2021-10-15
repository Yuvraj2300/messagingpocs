package com.sfg.jms.listener;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.sfg.jms.config.JmsConfig;
import com.sfg.jms.model.HelloWorldMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HelloMessageListener {

	private final JmsTemplate jmsTemplate;

	@JmsListener(destination = JmsConfig.MY_QUEUE)
	private void listen(@Payload HelloWorldMessage hellomessage, @Headers MessageHeaders headers, Message message) {
		//		System.out.println("Got message. Yayy!!!");
		//
		//		System.out.println(hellomessage);

		//uncomment to see how message retries works
		//throw new Exception("kboom");

	}

	@JmsListener(destination = JmsConfig.MY_SND_RCV_QUEUE)
	private void listenToSendReceiveQueue(@Payload HelloWorldMessage hellomessage, @Headers MessageHeaders headers, Message message)
			throws JmsException, JMSException {
		System.out.println("Got message. Yayy!!!");

		System.out.println(hellomessage);

		System.out.println("Giving a reply now.");


		// @formatter:off
		HelloWorldMessage payloadMsg = HelloWorldMessage
				.builder()
				.id(UUID.randomUUID())
				.message("World!!")
				.build(); 
// @formatter:on

		jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);

		//uncomment to see how message retries works
		//throw new Exception("kboom");

	}




}
