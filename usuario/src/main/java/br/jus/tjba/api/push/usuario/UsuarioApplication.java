package br.jus.tjba.api.push.usuario;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Usuario MS",
				description = "Microserviço de para o gerenciamento de usuario",
				version = "1.0",
				contact = @Contact(name = "TJBA", url = "www.tjba.jus.br")
		),
		servers = @Server(
				url = "localhost/usuarios/",
				description = "Servidor Local UsuarioMS",
				variables = {
						@ServerVariable(name = "serverUrl", defaultValue = "localhost"),
						@ServerVariable(name = "serverHttpPort", defaultValue = "8082")
				}))
public class UsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioApplication.class, args);
	}

}
