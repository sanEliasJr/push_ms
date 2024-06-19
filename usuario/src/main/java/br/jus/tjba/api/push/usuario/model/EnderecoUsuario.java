package br.jus.tjba.api.push.usuario.model;

import br.jus.tjba.api.push.usuario.enums.EstadosEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_endereco_usuario", schema = "client")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoUsuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client.tb_endereco_usuario_id_seq")
    @SequenceGenerator(name = "client.tb_endereco_usuario_id_seq", sequenceName = "client.tb_endereco_usuario_id_seq", allocationSize = 1)
    private Long id;
    @NotNull
    private String rua;
    @NotNull
    private String bairro;
    @NotNull
    private String numero;
    @NotNull
    private String cidade;
    @Enumerated(EnumType.STRING)
    @NotNull
    private EstadosEnum uf;
    @NotNull
    private String cep;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull
    private Usuario usuario;
}
