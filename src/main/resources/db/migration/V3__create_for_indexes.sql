CREATE TABLE IF NOT EXISTS warehouse.category
(
    id bigint NOT NULL,
    name character varying(255),
    CONSTRAINT category_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS warehouse.category
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS warehouse.indexes
(
    index bigint NOT NULL,
    code character varying(255),
    description character varying(255),
    name character varying(255),
    quantity double precision,
    category_id bigint,
    CONSTRAINT indexes_pkey PRIMARY KEY (index),
    CONSTRAINT fks81unba0gbosvsm43k7mhg0qv FOREIGN KEY (category_id)
        REFERENCES warehouse.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS warehouse.indexes
    OWNER to postgres;