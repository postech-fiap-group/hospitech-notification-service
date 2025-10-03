package notification.dto;

import java.time.LocalDate;

public record NotificationDTO(String pacienteNome,
		String pacienteEmail,
		String especialidadeConsulta,
		LocalDate dataConsulta,
		String medicoNome) {
}
