BEGIN;

-- Cria o tipo ENUM estatusregistration se ainda não existir
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_type WHERE typname = 'estatusregistration'
  ) THEN
    CREATE TYPE estatusregistration AS ENUM (
      'PENDENTE',
      'MATRICULADO',
      'CONCLUIDO',
      'CANCELADO'
    );
  END IF;
END $$;

-- Cria a tabela status_inscricao se ainda não existir
CREATE TABLE IF NOT EXISTS public.status_inscricao (
  id SERIAL PRIMARY KEY,
  status estatusregistration UNIQUE NOT NULL
);

-- Insere os valores enum somente se ainda não existirem
INSERT INTO public.status_inscricao (status)
SELECT 'PENDENTE'
WHERE NOT EXISTS (
  SELECT 1 FROM public.status_inscricao WHERE status = 'PENDENTE'
);

INSERT INTO public.status_inscricao (status)
SELECT 'MATRICULADO'
WHERE NOT EXISTS (
  SELECT 1 FROM public.status_inscricao WHERE status = 'MATRICULADO'
);

INSERT INTO public.status_inscricao (status)
SELECT 'CONCLUIDO'
WHERE NOT EXISTS (
  SELECT 1 FROM public.status_inscricao WHERE status = 'CONCLUIDO'
);

INSERT INTO public.status_inscricao (status)
SELECT 'CANCELADO'
WHERE NOT EXISTS (
  SELECT 1 FROM public.status_inscricao WHERE status = 'CANCELADO'
);

-- Adiciona a constraint de chave estrangeira somente se ainda não existir
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM information_schema.table_constraints
    WHERE constraint_name = 'fk_status_inscricao'
      AND table_name = 'inscricoes'
  ) THEN
    ALTER TABLE public.inscricoes
    ADD CONSTRAINT fk_status_inscricao
    FOREIGN KEY (status_id)
    REFERENCES public.status_inscricao(id);
  END IF;
END $$;

COMMIT;