-- Criação dos tipos ENUM se ainda não existirem
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'estatustransactions') THEN
        CREATE TYPE estatustransactions AS ENUM ('AGUARDANDO', 'APROVADO', 'PAGO', 'RECUSADO');
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'epaymentmethods') THEN
        CREATE TYPE epaymentmethods AS ENUM ('CARTAO', 'PIX', 'BOLETO');
    END IF;
END $$;

-- Criação da tabela 'transacoes' se não existir
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.tables 
        WHERE table_name = 'transacoes' AND table_schema = 'public'
    ) THEN
        CREATE TABLE public.transacoes (
            id SERIAL PRIMARY KEY,
            empresa_id INTEGER NOT NULL,
            cobranca_id INTEGER NOT NULL,
            metodo_pagamento epaymentmethods NOT NULL,
            valor NUMERIC(10,2),
            data_cobranca TIMESTAMP WITH TIME ZONE,
            status estatustransactions NOT NULL,
            data_notificacao TIMESTAMP WITH TIME ZONE
        );
    END IF;
END $$;

-- Criação do relacionamento com a tabela 'empresas' se não existir
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.table_constraints tc
        JOIN information_schema.key_column_usage kcu
            ON tc.constraint_name = kcu.constraint_name
        WHERE tc.constraint_type = 'FOREIGN KEY'
          AND tc.table_name = 'transacoes'
          AND kcu.column_name = 'empresa_id'
    ) THEN
        ALTER TABLE public.transacoes
        ADD CONSTRAINT fk_transacoes_empresas
        FOREIGN KEY (empresa_id)
        REFERENCES public.empresas (id);
    END IF;
END $$;