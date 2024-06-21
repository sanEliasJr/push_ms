-- Cria a tabela tb_sistema no schema client, caso ela não exista
CREATE TABLE IF NOT EXISTS client.tb_sistema (
    id SERIAL NOT NULL,
    sigla VARCHAR(10) NOT NULL,
    CONSTRAINT tb_sistema_pk PRIMARY KEY (id)
);

-- Insere as siglas PJE1G, PJE2G e PROJUDI na tabela client.tb_sistema
INSERT INTO client.tb_sistema (sigla)
VALUES
    ('PJE1G'),
    ('PJE2G'),
    ('PROJUDI');