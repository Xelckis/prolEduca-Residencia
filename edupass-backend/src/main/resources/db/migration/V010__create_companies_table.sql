BEGIN;

-- 1. Criando a tabela Empresas (Companies)
CREATE TABLE IF NOT EXISTS public.empresas (
  id SERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  cnpj VARCHAR(18) NOT NULL UNIQUE,
  nome_fantasia VARCHAR(255) NOT NULL
);

-- 2. Criando tabela de relacionamento entre empresas e roles
CREATE TABLE IF NOT EXISTS public.empresas_roles (
  companies_id INT NOT NULL,
  roles_id INT NOT NULL,
  PRIMARY KEY (companies_id, roles_id),
  FOREIGN KEY (companies_id) REFERENCES empresas(id) ON DELETE CASCADE,
  FOREIGN KEY (roles_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- 3. Adicionando a coluna empresa_id e a foreign key à tabela clientes
ALTER TABLE public.clientes
ADD COLUMN empresa_id INTEGER;

ALTER TABLE public.clientes
ADD CONSTRAINT fk_clientes_empresa FOREIGN KEY (empresa_id) REFERENCES empresas(id);

END;