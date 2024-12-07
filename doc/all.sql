DROP TABLE IF EXISTS test;

CREATE TABLE test (
    id BIGINT NOT NULL,
    name VARCHAR(50),
    password VARCHAR(50),
    PRIMARY KEY (id)
);

COMMENT ON COLUMN test.id IS 'id';
COMMENT ON COLUMN test.name IS 'name';
COMMENT ON COLUMN test.password IS 'password';
COMMENT ON TABLE test IS 'test';

INSERT INTO test (id, name, password) VALUES (1, 'test', 'password');


DROP TABLE IF EXISTS "demo";

CREATE TABLE "demo" (
    "id" bigint NOT NULL,
    "name" character varying(50),
    PRIMARY KEY ("id")
);

COMMENT ON TABLE "demo" IS 'test';
COMMENT ON COLUMN "demo"."id" IS 'id';
COMMENT ON COLUMN "demo"."name" IS 'name';

INSERT INTO "demo" ("id", "name") VALUES (1, 'test');