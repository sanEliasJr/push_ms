package br.jus.tjba.api.push.usuario.pub.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
@EqualsAndHashCode
public class DefaultMessage {
    private Date timestamp;
    private int status;
    private String message;
    private String path;

    public void setMessage(int status, String path, String message) {
        this.timestamp = new Date();
        this.status = status;
        this.path = path;
        this.message = message;
    }
}
