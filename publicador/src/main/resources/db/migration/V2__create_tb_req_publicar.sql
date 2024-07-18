-- V2__create_tb_req_publicar.sql

-- Table: tb_req_publicar
CREATE TABLE client.tb_req_publicar (
            id serial  NOT NULL,
            numero_processo varchar(25)  NOT NULL,
            sigla varchar(50)  NOT NULL,
            mensagem varchar(2000)  NOT NULL,
            CONSTRAINT tb_req_publicar_pk PRIMARY KEY (id)
);