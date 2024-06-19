package br.jus.tjba.api.push.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_usuario_processo_sistema", schema = "client")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioProcessoSistema implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client.tb_usuario_processo_sistema_id_seq")
    @SequenceGenerator(name = "client.tb_usuario_processo_sistema_id_seq", sequenceName = "client.tb_usuario_processo_sistema_id_seq", allocationSize = 1)
    private Long id;
    @NotNull
    private String numeroProcesso;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "sistema_id", nullable = false)
    @NotNull
    private Sistema sistema;
}
