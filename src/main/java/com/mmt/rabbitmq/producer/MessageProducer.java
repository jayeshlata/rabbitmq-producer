/**
 * 
 */
package com.mmt.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MMT6401
 *
 */
@Component
public class MessageProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(String queue, String routingKey, String message) {
		System.out.println("Sending message: " + message);

		rabbitTemplate.convertAndSend(message);
	}

	public void sendMessage(String message) {
		System.out.println("Sending message: " + message);

		rabbitTemplate.convertAndSend(message);
	}
}