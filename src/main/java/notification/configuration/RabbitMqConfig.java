package notification.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String EXCHANGE_NAME = "notification-exchange";
	public static final String NOTIFICATION_QUEUE = "notification-queue";
	public static final String ROUTING_KEY_NEW = "notification.new";

	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue notificationQueue(){
		return new Queue(NOTIFICATION_QUEUE,true);
	}

	@Bean
	public Binding notificationBind(Queue queue, DirectExchange directExchange){

		return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY_NEW);
	}

	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){

		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());

		return rabbitTemplate;
	}

}
