package br.jus.tjba.api.push.publicador.amqp;

import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublicadorAMQPConfiguration {

    @Bean
    public Queue criaFila(){
        return QueueBuilder.durable("sinalizar").build();
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> criaListener(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}
