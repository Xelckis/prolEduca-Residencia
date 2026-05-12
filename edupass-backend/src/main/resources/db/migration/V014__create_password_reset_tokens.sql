-- public.password_reset_tokens definição

-- Drop table

-- DROP TABLE public.password_reset_tokens;

CREATE TABLE IF NOT EXISTS password_reset_tokens (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    token VARCHAR(255) NOT NULL,
    expiration_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES clientes(id) ON DELETE CASCADE
);