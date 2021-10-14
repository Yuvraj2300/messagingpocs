package com.sfg.jms.listener;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.sfg.jms.config.JmsConfig;
import com.sfg.jms.model.HelloWorldMessage;

@Component
public class HelloMessageListener {

	@JmsListener(destination = JmsConfig.MY_QUEUE)
	private void listen(@Payload HelloWorldMessage hellomessage, @Headers MessageHeaders headers, Message message) {
		System.out.println("Got message. Yayy!!!");

		System.out.println(hellomessage);
	}
}
