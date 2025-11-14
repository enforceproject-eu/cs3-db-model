CREATE TABLE IF NOT EXISTS public.cs3_data
(
    id int NOT NULL,
    fictionary int,
    date_of_report character varying(255),
    description character varying(1024),
    municipality character varying(100),
    coords geometry,
    responses character varying(1024),
    CONSTRAINT cs3_data_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.cs3_data_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;
