-- V1__create_tb_usuario.sql

-- Table: tb_usuario
CREATE TABLE client.tb_usuario (
        id serial  NOT NULL,
        nome varchar(100) NOT NULL,
        login varchar(200)  NOT NULL,
        senha varchar(150)  NOT NULL,
        cpf varchar(14)  NOT NULL,
        CONSTRAINT tb_usuario_pk PRIMARY KEY (id)
);