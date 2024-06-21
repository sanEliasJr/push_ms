-- V2__create_tb_sistema.sql

-- Table: tb_sistema
CREATE TABLE tb_sistema (
        id serial  NOT NULL,
        sigla varchar(10)  NOT NULL,
        CONSTRAINT tb_sistema_pk PRIMARY KEY (id)
);