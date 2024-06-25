package br.jus.tjba.api.push.usuario.pub.service;

import br.jus.tjba.api.push.usuario.pub.model.DefaultMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    private final DefaultMessage message;
    private final HttpServletRequest httpServletRequest;

    public ResponseService(
            DefaultMessage message,
            HttpServletRequest httpServletRequest
    ) {
        this.message = message;
        this.httpServletRequest = httpServletRequest;
    }

    public ResponseEntity<Object> sendMessage(String text, HttpStatus httpStatus) {
        message.setMessage(httpStatus.value(), httpServletRequest.getRequestURI(), text);
        return ResponseEntity.status(httpStatus).body(message);
    }
}