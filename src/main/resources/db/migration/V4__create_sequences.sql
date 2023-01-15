CREATE SEQUENCE IF NOT EXISTS warehouse.user_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    CACHE 1;

ALTER SEQUENCE warehouse.user_seq
    OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS warehouse.index_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    CACHE 1;

ALTER SEQUENCE warehouse.index_seq
    OWNER TO postgres;