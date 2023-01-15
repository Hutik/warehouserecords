CREATE TABLE IF NOT EXISTS warehouse.role
(
    id integer NOT NULL,
    name character varying(255),
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS warehouse.role
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS warehouse.users
(
    id bigint NOT NULL,
    last_name character varying(255),
    name character varying(255),
    password character varying(255),
    role character varying(255),
    username character varying(255),
    avatar bytea,
    email text,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS warehouse.users
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS warehouse.user_roles
(
    user_id bigint NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id)
        REFERENCES warehouse.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkrhfovtciq1l558cw6udg0h0d3 FOREIGN KEY (role_id)
        REFERENCES warehouse.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS warehouse.user_roles
    OWNER to postgres;