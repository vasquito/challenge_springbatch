CREATE TABLE IF NOT EXISTS sales (
    id bigserial PRIMARY KEY,
    point_sale_id integer NOT NULL,
    amount decimal(20,2) NOT NULL,
    quantity integer NOT NULL,
    taxes decimal(20,2) NOT NULL,
    created_at timestamp NOT NULL
);