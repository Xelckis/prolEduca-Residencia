BEGIN;

CREATE TABLE IF NOT EXISTS public.roles
(
    id serial PRIMARY KEY,
    type character varying(255) COLLATE pg_catalog."default" UNIQUE NOT NULL
);

INSERT INTO public.roles (type) VALUES 
    ('ROLE_USER'),
    ('ROLE_ADMIN'),
    ('ROLE_COMPANY'),
    ('ROLE_INSTITUTION');

CREATE TABLE IF NOT EXISTS clientes_roles (
    customers_id INT NOT NULL,
    roles_id INT NOT NULL,
    PRIMARY KEY (customers_id, roles_id),
    FOREIGN KEY (customers_id) REFERENCES clientes(id) ON DELETE CASCADE,
    FOREIGN KEY (roles_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS administradores_roles (
    administrators_id INT NOT NULL,
    roles_id INT NOT NULL,
    PRIMARY KEY (administrators_id, roles_id),
    FOREIGN KEY (administrators_id) REFERENCES administradores(id) ON DELETE CASCADE,
    FOREIGN KEY (roles_id) REFERENCES roles(id) ON DELETE CASCADE
);

END;