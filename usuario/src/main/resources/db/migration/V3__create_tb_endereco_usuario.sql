-- V1__create_tb_endereco_usuario.sql

CREATE TABLE client.tb_endereco_usuario (
        id SERIAL PRIMARY KEY,
        rua VARCHAR(100) NOT NULL,
        bairro VARCHAR(100) NOT NULL,
        numero VARCHAR(4) NOT NULL,
        cidade VARCHAR(100) NOT NULL,
        uf VARCHAR(2) NOT NULL,
        cep VARCHAR(9) NOT NULL,
        usuario_id INT NOT NULL,
        CONSTRAINT fk_tb_endereco_usuario_tb_usuario
                FOREIGN KEY (usuario_id)
                REFERENCES tb_usuario (id)
);
