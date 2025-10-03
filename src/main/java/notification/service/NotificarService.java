package notification.service;

import notification.command.NotificarCommand;
import notification.configuration.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificarService {

	private final RabbitTemplate rabbitTemplate;

	public NotificarService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void newNotitification(NotificarCommand notificarCmd){

		rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY_NEW, notificarCmd);
	}

}
