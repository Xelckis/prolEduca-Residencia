BEGIN;

CREATE TABLE IF NOT EXISTS public.clientes_autorizados
(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome varchar (255),
    cpf varchar(255),
    rg varchar(255),
    email varchar(255),
    telefone varchar(255),
    estado varchar(255),
    cidade varchar(255),
    bairro varchar(255),
    empresa_id int(4)
);

END;