DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns 
        WHERE table_schema = 'public'
          AND table_name = 'cursos'
          AND column_name = 'ano_bolsa'
    ) THEN
        ALTER TABLE public.cursos ADD COLUMN ano_bolsa VARCHAR(255);
    END IF;
END$$;
