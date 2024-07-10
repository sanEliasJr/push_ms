package br.jus.tjba.api.push.publicador.controller;

import br.jus.tjba.api.push.publicador.dto.SinalizarDTO;
import br.jus.tjba.api.push.publicador.service.PublicadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PublicadorController {

    @Autowired
    private PublicadorService publicadoService;

    @PostMapping("/sinalizar")
    public ResponseEntity<String> sinalizar(@RequestBody @Valid SinalizarDTO sinalizarDTO){
        return publicadoService.sinalizar(sinalizarDTO);
    }

}
