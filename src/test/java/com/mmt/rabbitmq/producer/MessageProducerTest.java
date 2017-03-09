package com.mmt.rabbitmq.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mmt.rabbitmq.producer.config.ProducerConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProducerConfig.class)
public class MessageProducerTest {

	@Autowired
	private MessageProducer messageProducer;

	static {
		System.setProperty("core.exchange", "exchangeOne");
		System.setProperty("core.routing.key", "keyOne");
		System.setProperty("core.queue", "queueOne");
	}

	@Test
	public void testSendMessage() {
		messageProducer.sendMessage("hello");
	}
}