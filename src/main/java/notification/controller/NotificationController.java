package notification.controller;

import notification.command.NotificarCommand;
import notification.dto.NotificationDTO;
import notification.service.NotificarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	private final NotificarService notificarService;

	public NotificationController(NotificarService notificarService) {
		this.notificarService = notificarService;
	}

	@PostMapping("/new")
	public ResponseEntity<String> newNotification(@RequestBody NotificationDTO dto){
		notificarService.newNotitification(new NotificarCommand(dto.pacienteNome(),dto.pacienteEmail(),
				dto.especialidadeConsulta(), dto.dataConsulta(), dto.medicoNome()));

		return ResponseEntity.ok("Notificação enviada para a queue");
	}

}
