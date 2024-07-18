package br.jus.tjba.api.push.publicador.controller;

import br.jus.tjba.api.push.publicador.dto.ResponseUsuariosDTO;
import br.jus.tjba.api.push.publicador.dto.SinalizarDTO;
import br.jus.tjba.api.push.publicador.service.PublicarService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PublicarController {

    @Autowired
    private PublicarService publicarService;

    @PostMapping("/sinalizar")
    public ResponseEntity<String> sinalizar(@RequestBody @Valid SinalizarDTO sinalizarDTO){
        return publicarService.sinalizar(sinalizarDTO);
    }

}
