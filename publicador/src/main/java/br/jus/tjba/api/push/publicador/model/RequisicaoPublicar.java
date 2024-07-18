package br.jus.tjba.api.push.publicador.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_req_publicar", schema = "client")
public class RequisicaoPublicar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client.tb_req_publicar_id_seq")
    @SequenceGenerator(name = "client.tb_req_publicar_id_seq", sequenceName = "client.tb_req_publicar_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "numero_processo")
    @NotNull
    private String numeroProcesso;
    @NotNull
    private String sigla;
    @NotNull
    private String mensagem;
}
