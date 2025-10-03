package notification.service;

import jakarta.mail.internet.MimeMessage;
import notification.command.NotificarCommand;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailService {

	private JavaMailSender mailSender;

	public void enviarEmailSimples(NotificarCommand cmd) {

		try {
			MimeMessage mensagem = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

			helper.setTo(cmd.pacienteEmail());
			helper.setSubject("Confirmação de Consulta - Rede Hospitech");

			String conteudoHtml = criarTemplateConfirmacaoConsulta(cmd.pacienteNome(), cmd.especialidadeConsulta(),
					cmd.medicoNome(), cmd.dataConsulta());
			helper.setText(conteudoHtml, true);

			mailSender.send(mensagem);
		}
		catch (Exception e){
			System.out.println("Erro ao enviar email: " + e.getMessage());

		}

	}

	private String criarTemplateConfirmacaoConsulta(String nome, String especialidade, String medico, LocalDate data) {
		return """
            <!DOCTYPE html>
            <html lang="pt-BR">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Confirmação de Consulta</title>
                <style>
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                    }
                    
                    body {
                        font-family: 'Arial', sans-serif;
                        line-height: 1.6;
                        color: #333;
                        background-color: #f4f4f4;
                        padding: 20px;
                    }
                    
                    .container {
                        max-width: 600px;
                        margin: 0 auto;
                        background: white;
                        border-radius: 10px;
                        overflow: hidden;
                        box-shadow: 0 0 20px rgba(0,0,0,0.1);
                    }
                    
                    .header {
                        background: linear-gradient(135deg, #0055a5, #0077cc);
                        color: white;
                        padding: 30px 20px;
                        text-align: center;
                    }
                    
                    .header h1 {
                        font-size: 24px;
                        margin-bottom: 10px;
                        font-weight: bold;
                    }
                    
                    .header p {
                        font-size: 16px;
                        opacity: 0.9;
                    }
                    
                    .content {
                        padding: 30px;
                    }
                    
                    .saudacao {
                        font-size: 18px;
                        margin-bottom: 25px;
                        color: #0055a5;
                        font-weight: bold;
                    }
                    
                    .info-box {
                        background: #f8f9fa;
                        border-left: 4px solid #0055a5;
                        padding: 20px;
                        margin-bottom: 25px;
                        border-radius: 0 8px 8px 0;
                    }
                    
                    .info-item {
                        margin-bottom: 15px;
                        display: flex;
                        align-items: flex-start;
                    }
                    
                    .info-label {
                        font-weight: bold;
                        color: #0055a5;
                        min-width: 120px;
                        margin-right: 15px;
                    }
                    
                    .info-value {
                        color: #333;
                        flex: 1;
                    }
                    
                    .alert {
                        background: #fff3cd;
                        border: 1px solid #ffeaa7;
                        border-radius: 8px;
                        padding: 20px;
                        margin-top: 25px;
                    }
                    
                    .alert-title {
                        font-weight: bold;
                        color: #856404;
                        margin-bottom: 10px;
                        display: flex;
                        align-items: center;
                    }
                    
                    .alert-title:before {
                        content: "⚠️";
                        margin-right: 8px;
                    }
                    
                    .footer {
                        background: #2c3e50;
                        color: white;
                        padding: 20px;
                        text-align: center;
                        font-size: 12px;
                    }
                    
                    .logo {
                        text-align: center;
                        margin-bottom: 20px;
                    }
                    
                    .logo-text {
                        font-size: 20px;
                        font-weight: bold;
                        color: #0055a5;
                    }
                    
                    @media (max-width: 600px) {
                        .info-item {
                            flex-direction: column;
                        }
                        
                        .info-label {
                            min-width: auto;
                            margin-bottom: 5px;
                        }
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="logo">
                        <div class="logo-text">Rede Hospitech</div>
                    </div>
                    
                    <div class="header">
                        <h1>✅ Consulta Confirmada</h1>
                        <p>Sua consulta foi agendada com sucesso</p>
                    </div>
                    
                    <div class="content">
                        <div class="saudacao">
                            Olá %s,
                        </div>
                        
                        <p style="margin-bottom: 20px; color: #666;">
                            Sua consulta foi marcada com sucesso na Rede Hospitech, confira:
                        </p>
                        
                        <div class="info-box">
                            <div class="info-item">
                                <span class="info-label">Dia:</span>
                                <span class="info-value">%s</span>
                            </div>
                                                        
                            <div class="info-item">
                                <span class="info-label">Médico:</span>
                                <span class="info-value">%s</span>
                            </div>
                            
                            <div class="info-item">
                                <span class="info-label">Especialidade:</span>
                                <span class="info-value">%s</span>
                            </div>                            
                           
                        </div>
                        
                        <div class="alert">
                            <div class="alert-title">Importante</div>
                            <p style="color: #856404; margin: 0;">
                                Lembramos que, para consultas em nossas unidades, a apresentação do documento de identificação e da carteirinha do convênio*, são indispensáveis.
                            </p>
                        </div>
                        
                        <p style="margin-top: 25px; color: #666; font-size: 14px; text-align: center;">
                            Em caso de dúvidas, entre em contato com nossa central de atendimento.
                        </p>
                    </div>
                    
                    <div class="footer">
                        <p>Rede Hospitech © 2025 - Todos os direitos reservados</p>
                        <p style="margin-top: 5px; opacity: 0.8;">
                            *Consulte condições do seu convênio
                        </p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(
				nome,
				data,
				medico,
				especialidade
		);
	}
}

