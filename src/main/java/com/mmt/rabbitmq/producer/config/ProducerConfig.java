/**
 * 
 */
package com.mmt.rabbitmq.producer.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author MMT6401
 *
 */
@Configuration
@ComponentScan(basePackages = "com.mmt.rabbitmq")
public class ProducerConfig {

	@Value(value = "${core.exchange}")
	private String exchange;

	@Value(value = "${core.routing.key}")
	private String routingKey;

	@Value(value = "${core.queue}")
	private String queue;

	@Bean
	public Queue rabbitQueue() {
		return new Queue(queue);
	}

	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");

		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		// connectionFactory.setUri("amqp://guest:guest@localhost:5672");
		return connectionFactory;
	}

	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		// queue & routing key to be provide by the host application on runtime
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setExchange(exchange);
		template.setQueue(queue);
		template.setRoutingKey(routingKey);
		return template;
	}

	@Bean
	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	/*
	 * @Bean public DirectExchange directExchange() { return new
	 * DirectExchange(exchange, true, false); }
	 * 
	 * @Bean Binding exchangeQueueBinding(DirectExchange exchange, Queue queue)
	 * { return BindingBuilder.bind(queue).to(exchange).with(routingKey); }
	 */

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(exchange, true, false);
	}

	@Bean
	Binding exchangeQueueBinding(TopicExchange exchange, Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
}