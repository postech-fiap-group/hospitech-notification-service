package notification.command;

import java.time.LocalDate;

public record NotificarCommand(String pacienteNome,
		String pacienteEmail,
		String especialidadeConsulta,
		LocalDate dataConsulta,
		String medicoNome) {
}