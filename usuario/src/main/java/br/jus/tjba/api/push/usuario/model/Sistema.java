package br.jus.tjba.api.push.usuario.model;

import br.jus.tjba.api.push.usuario.enums.SiglaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_sistema", schema = "client")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sistema implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client.tb_sistema_id_seq")
    @SequenceGenerator(name = "client.tb_sistema_id_seq", sequenceName = "client.tb_sistema_id_seq", allocationSize = 1)
    private Long id;
    @NotNull
    private String sigla;
}
