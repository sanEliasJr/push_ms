package br.jus.tjba.api.push.publicador.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_mensagem_pendente", schema = "client")
public class MensagemPendente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client.tb_mensagem_pendente_id_seq")
    @SequenceGenerator(name = "client.tb_mensagem_pendente_id_seq", sequenceName = "client.tb_mensagem_pendente_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "numero_processo")
    @NotNull
    private String numeroProcesso;
    @NotNull
    private String email;
    @NotNull
    private String nome;
    @NotNull
    private String mensagem;
}
