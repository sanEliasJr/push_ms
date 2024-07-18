package br.jus.tjba.api.push.publicador.jobs;

import br.jus.tjba.api.push.publicador.dto.ProcessoSistemaDTO;
import br.jus.tjba.api.push.publicador.dto.ResponseUsuariosDTO;
import br.jus.tjba.api.push.publicador.dto.SinalizarDTO;
import br.jus.tjba.api.push.publicador.dto.UsuarioDTO;
import br.jus.tjba.api.push.publicador.http.UsuarioClient;
import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import br.jus.tjba.api.push.publicador.model.RequisicaoPublicar;
import br.jus.tjba.api.push.publicador.repository.RequisicaoPublicarRepository;
import br.jus.tjba.api.push.publicador.service.PublicarService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicarJob {

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private RequisicaoPublicarRepository requisicaoPublicarRepository;

    @Autowired
    private PublicarService publicarService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


//    @Scheduled(fixedRate = 60000)
//    public void publicar() {
//        if(servicoUsuarioDisponivel()){
//            List<RequisicaoPublicar> requisicaoPublicarList = requisicaoPublicarRepository.findAll();
//            for(RequisicaoPublicar requisicaoPublicar : requisicaoPublicarList) {
//                publicarService.sinalizar(SinalizarDTO.builder()
//                        .numeroProcesso(requisicaoPublicar.getNumeroProcesso())
//                        .sigla(requisicaoPublicar.getSigla())
//                        .mensagem(requisicaoPublicar.getMensagem())
//                        .build());
//            }
//        }
//    }
//
//    private boolean servicoUsuarioDisponivel() {
//        // Implemente lógica para verificar se o serviço de usuário está disponível
//        // Exemplo usando Feign Client ou outra biblioteca HTTP
//        try {
//            // Exemplo de verificação usando Feign Client
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
////    private ResponseUsuariosDTO buscarRegistrosPendentesDoBanco() {
//        // Implemente lógica para buscar registros pendentes no banco de dados
//        // Retorna uma lista de MensagemPendente
//    }
}
