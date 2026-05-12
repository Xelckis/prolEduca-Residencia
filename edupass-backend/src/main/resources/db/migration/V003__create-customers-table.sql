BEGIN;

CREATE TABLE IF NOT EXISTS public.clientes (
	id SERIAL PRIMARY KEY,
	nome_completo VARCHAR(255),
	email VARCHAR(255),
	telefone VARCHAR(255),
	cpf VARCHAR(255) UNIQUE,
	data_nascimento DATE,
	status BOOLEAN,
	senha VARCHAR(255),
	cep VARCHAR(255),
	logradouro VARCHAR(255),
	numero VARCHAR(255),
	complemento VARCHAR(255),
	bairro VARCHAR(255),
	cidade VARCHAR(255),
	estado VARCHAR(255),
	cargo VARCHAR(255),
	setor VARCHAR(255),
	email_corporativo VARCHAR(255),
	telefone_comercial VARCHAR(255)
);

END;