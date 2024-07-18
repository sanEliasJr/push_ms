package br.jus.tjba.api.push.notificador.service;

import br.jus.tjba.api.push.notificador.dto.MensagemUsuarioDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificadorService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @RabbitListener(queues = "sinalizar")
    public void receberMensagem(MensagemUsuarioDTO mensagemUsuario){
        sendEmail(mensagemUsuario);
    }

    private void sendEmail(MensagemUsuarioDTO mensagemUsuarioDTO){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mensagemUsuarioDTO.email());
        mailMessage.setSubject("Sistema "+mensagemUsuarioDTO.sistema()+" | Movimentação no Processo "+ mensagemUsuarioDTO.numeroProcesso());
        mailMessage.setText("Olá " + mensagemUsuarioDTO.nome()+"\nSegue a mensagem abaixo\n"+ mensagemUsuarioDTO.mensagem());
        mailMessage.setFrom(fromEmail);
        mailSender.send(mailMessage);
    }

}
