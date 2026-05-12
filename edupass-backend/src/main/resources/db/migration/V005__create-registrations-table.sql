BEGIN;

CREATE TABLE IF NOT EXISTS public.inscricoes (
    id SERIAL PRIMARY KEY,
    data_inscricao DATE,
    status_id INTEGER NOT NULL,
    curso_id INTEGER NOT NULL,
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos (id),
    bolsista_id INTEGER NOT NULL,
    CONSTRAINT fk_bolsista FOREIGN KEY (bolsista_id) REFERENCES bolsistas (id)
);

END;