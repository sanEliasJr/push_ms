package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.dto.*;
import br.jus.tjba.api.push.publicador.http.UsuarioClient;
import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import br.jus.tjba.api.push.publicador.model.RequisicaoPublicar;
import br.jus.tjba.api.push.publicador.repository.MensagemRepository;
import br.jus.tjba.api.push.publicador.repository.RequisicaoPublicarRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublicarService {
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private MensagemRepository mensagemRepository;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    RequisicaoPublicarRepository requisicaoPublicarRespository;
    @CircuitBreaker(name = "sinalizarProcesso", fallbackMethod = "guardaNoBanco")
    public ResponseEntity<String> sinalizar(SinalizarDTO sinalizarDTO){
        ResponseUsuariosDTO usuarios =  usuarioClient.obterUsuariosPorSistemaEProcesso(ProcessoSistemaDTO.builder()
                        .numeroProcesso(sinalizarDTO.numeroProcesso())
                        .sistema(sinalizarDTO.sigla())
                        .build());
        for(UsuarioDTO usuario : usuarios.usuarios()){
            MensagemPendente mensagemPendente = new MensagemPendente();
            mensagemPendente.setMensagem(sinalizarDTO.mensagem());
            mensagemPendente.setNumeroProcesso(usuarios.numeroProcesso());
            try{
                mensagemPendente.setEmail(usuario.login());
                mensagemPendente.setNome(usuario.nome());
                amqpTemplate.convertAndSend("sinalizar", mensagemPendente);
            } catch (AmqpException e){
                mensagemPendente.setEmail(usuario.login());
                mensagemPendente.setNome(usuario.nome());
                rabbitMqFallback(mensagemPendente, e);
            }
        }
        return ResponseEntity.ok().body("Deu certo!");
    }

    public ResponseEntity<String> gravarNoBanco(SinalizarDTO sinalizarDTO, Exception e) {
        RequisicaoPublicar req = RequisicaoPublicar.builder()
                .numeroProcesso(sinalizarDTO.numeroProcesso())
                .sigla(sinalizarDTO.sigla())
                .mensagem(sinalizarDTO.mensagem())
                .build();
        requisicaoPublicarRespository.save(req);
        return ResponseEntity.ok().body("Deu certo!");
    }

    private void rabbitMqFallback(MensagemPendente mensagemPendente, Exception e){
        mensagemRepository.save(mensagemPendente);
    }
}