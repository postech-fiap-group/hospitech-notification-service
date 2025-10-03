package notification.consumer;

import lombok.AllArgsConstructor;
import notification.command.NotificarCommand;
import notification.configuration.RabbitMqConfig;
import notification.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationConsumer {
	private final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
	private final EmailService emailService;

	@RabbitListener(queues = RabbitMqConfig.NOTIFICATION_QUEUE)
	public void processNotification(NotificarCommand command)  {
		emailService.enviarEmailSimples(command);

		log.info("Lembrete de Consulta enviado com sucesso!");
		log.info("Paciente: " + command.pacienteNome());
		log.info("Email: " + command.pacienteEmail());
		log.info("Médico: " + command.medicoNome());
		log.info("Data da Consulta: " + command.dataConsulta());
	}

}
