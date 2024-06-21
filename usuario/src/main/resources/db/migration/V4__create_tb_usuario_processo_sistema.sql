-- V4__create_tb_usuario_processo_sistema.sql

CREATE TABLE client.tb_usuario_processo_sistema (
            id SERIAL PRIMARY KEY,
            numero_processo VARCHAR(25) NOT NULL,
            usuario_id INT NOT NULL,
            sistema_id INT NOT NULL,
                CONSTRAINT fk_tb_usuario_processo_sistema_tb_usuario
                    FOREIGN KEY (usuario_id)
                        REFERENCES client.tb_usuario (id),
                CONSTRAINT fk_tb_usuario_processo_sistema_tb_sistema
                    FOREIGN KEY (sistema_id)
                        REFERENCES client.tb_sistema (id)
);