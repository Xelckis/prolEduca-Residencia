DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'empresas' AND column_name = 'numero_telefone'
    ) THEN
        ALTER TABLE public.empresas ADD COLUMN numero_telefone VARCHAR;
    END IF;
END
$$;
