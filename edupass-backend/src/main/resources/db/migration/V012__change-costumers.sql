-- Verifica e adiciona a coluna codigo_recuperacao se não existir
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='clientes' 
          AND column_name='codigo_recuperacao'
    ) THEN
        ALTER TABLE clientes ADD COLUMN codigo_recuperacao VARCHAR;
    END IF;
END
$$;

-- Verifica e adiciona a coluna expiracao_codigo se não existir
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_name='clientes' 
          AND column_name='expiracao_codigo'
    ) THEN
        ALTER TABLE clientes ADD COLUMN expiracao_codigo TIMESTAMP;
    END IF;
END
$$;
