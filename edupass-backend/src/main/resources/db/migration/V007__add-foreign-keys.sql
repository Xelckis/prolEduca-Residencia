BEGIN;

ALTER TABLE public.bolsistas
    ADD CONSTRAINT fk_bolsistas_cliente FOREIGN KEY (cliente_id)
    REFERENCES public.clientes (id);

ALTER TABLE public.cursos
    ADD CONSTRAINT fk_cursos_instituicao FOREIGN KEY (instituicao_id)
    REFERENCES public.instituicoes (id);

END;