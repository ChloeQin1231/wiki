DROP TABLE IF EXISTS test;

CREATE TABLE test (
    id BIGINT NOT NULL,
    name VARCHAR(50),
    PRIMARY KEY (id)
);

COMMENT ON COLUMN test.id IS 'id';
COMMENT ON COLUMN test.name IS 'name';
COMMENT ON TABLE test IS 'test';
