-- V1__create_tb_mensagem_pendente.sql

-- Table: tb_mensagem_pendente
CREATE TABLE client.tb_mensagem_pendente (
            id serial  NOT NULL,
            numero_processo varchar(25)  NOT NULL,
            email varchar(50)  NOT NULL,
            nome varchar(50)  NOT NULL,
            mensagem varchar(2000)  NOT NULL,
            CONSTRAINT tb_mensagem_pendente_pk PRIMARY KEY (id)
);