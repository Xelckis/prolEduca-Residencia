BEGIN;

-- Adiciona novos campos na tabela de clientes
ALTER TABLE public.clientes
ADD COLUMN IF NOT EXISTS cargo VARCHAR(255);

ALTER TABLE public.clientes
ADD COLUMN IF NOT EXISTS setor VARCHAR(255);

ALTER TABLE public.clientes
ADD COLUMN IF NOT EXISTS email_corporativo VARCHAR(255);

ALTER TABLE public.clientes
ADD COLUMN IF NOT EXISTS telefone_comercial VARCHAR(255);

-- Adiciona novos campos na tabela de instituicoes
ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS cnpj VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS nome_fantasia VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS razao_social VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS responsavel_financeiro VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS telefone_financeiro VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS segmentos VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS empresa VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS resp_nome VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS resp_nascimento VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS resp_telefone VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS resp_email VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS resp_rg VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS resp_cpf VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS op_nome VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS op_telefone1 VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS op_telefone2 VARCHAR(255);

ALTER TABLE public.instituicoes
ADD COLUMN IF NOT EXISTS op_email VARCHAR(255);

END;