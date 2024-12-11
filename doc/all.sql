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


-- Drop table ebook if it exists
DROP TABLE IF EXISTS ebook;
-- Create ebook table
CREATE TABLE ebook (
    id BIGINT NOT NULL, -- ID
    name VARCHAR(50), -- Name
    category1_id BIGINT, -- Category 1
    category2_id BIGINT, -- Category 2
    description VARCHAR(200), -- Description
    cover VARCHAR(200), -- Cover
    doc_count INT NOT NULL DEFAULT 0, -- Document Count
    view_count INT NOT NULL DEFAULT 0, -- View Count
    vote_count INT NOT NULL DEFAULT 0, -- Vote Count
    PRIMARY KEY (id)
);

COMMENT ON TABLE "ebook" IS 'E-book information';
COMMENT ON COLUMN "ebook"."id" IS 'ID';
COMMENT ON COLUMN "ebook"."name" IS 'Name';
COMMENT ON COLUMN "ebook"."category1_id" IS 'Category 1';
COMMENT ON COLUMN "ebook"."category2_id" IS 'Category 2';
COMMENT ON COLUMN "ebook"."description" IS 'Description';
COMMENT ON COLUMN "ebook"."cover" IS 'Cover';
COMMENT ON COLUMN "ebook"."doc_count" IS 'Document count';
COMMENT ON COLUMN "ebook"."view_count" IS 'View count';
COMMENT ON COLUMN "ebook"."vote_count" IS 'Vote count';

-- Insert sample data into ebook
INSERT INTO ebook (id, name, description) VALUES
                                              (1, 'Spring Boot Beginner Guide', 'A beginner-friendly Java development framework for enterprise applications.'),
                                              (2, 'Vue Beginner Guide', 'A beginner-friendly Vue development framework for enterprise applications.'),
                                              (3, 'Python Beginner Guide', 'A beginner-friendly Python development framework for enterprise applications.'),
                                              (4, 'MySQL Beginner Guide', 'A beginner-friendly MySQL development framework for enterprise applications.'),
                                              (5, 'Oracle Beginner Guide', 'A beginner-friendly Oracle development framework for enterprise applications.');




-- Drop table category if it exists
DROP TABLE IF EXISTS category;
-- Create category table
CREATE TABLE category (
    id BIGINT NOT NULL, -- ID
    parent BIGINT NOT NULL DEFAULT 0, -- Parent ID
    name VARCHAR(50) NOT NULL, -- Name
    sort INT, -- Sort Order
    PRIMARY KEY (id)
);

comment on table category is 'Categories';
comment on column category.id is 'ID';
comment on column category.parent is 'Parent ID';
comment on column category.name is 'Name';
comment on column category.sort is 'Sort order';

-- Insert sample data into category
INSERT INTO category (id, parent, name, sort) VALUES
                                                  (100, 0, 'Frontend Development', 100),
                                                  (101, 100, 'Vue', 101),
                                                  (102, 100, 'HTML & CSS', 102),
                                                  (200, 0, 'Java', 200),
                                                  (201, 200, 'Basic Applications', 201),
                                                  (202, 200, 'Framework Applications', 202),
                                                  (300, 0, 'Python', 300),
                                                  (301, 300, 'Basic Applications', 301),
                                                  (302, 300, 'Advanced Applications', 302),
                                                  (400, 0, 'Database', 400),
                                                  (401, 400, 'MySQL', 401),
                                                  (500, 0, 'Others', 500),
                                                  (501, 500, 'Servers', 501),
                                                  (502, 500, 'Development Tools', 502),
                                                  (503, 500, 'Popular Server Languages', 503);

-- Drop table doc if it exists
DROP TABLE IF EXISTS doc;
-- Create doc table
CREATE TABLE doc (
                     id BIGINT NOT NULL, -- ID
                     ebook_id BIGINT NOT NULL DEFAULT 0, -- Ebook ID
                     parent BIGINT NOT NULL DEFAULT 0, -- Parent ID
                     name VARCHAR(50) NOT NULL, -- Name
                     sort INT, -- Sort Order
                     view_count INT DEFAULT 0, -- View Count
                     vote_count INT DEFAULT 0, -- Vote Count
                     PRIMARY KEY (id)
);

comment on table doc is 'Documents';
comment on column doc.id is 'ID';
comment on column doc.ebook_id is 'Ebook ID';
comment on column doc.parent is 'Parent ID';
comment on column doc.name is 'Name';
comment on column doc.sort is 'Sort order';
comment on column doc.view_count is 'View count';
comment on column doc.vote_count is 'Vote count';

-- Insert sample data into doc
INSERT INTO doc (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES
                                                                               (1, 1, 0, 'Document 1', 1, 0, 0),
                                                                               (2, 1, 1, 'Document 1.1', 1, 0, 0),
                                                                               (3, 1, 0, 'Document 2', 2, 0, 0),
                                                                               (4, 1, 3, 'Document 2.1', 1, 0, 0),
                                                                               (5, 1, 3, 'Document 2.2', 2, 0, 0),
                                                                               (6, 1, 5, 'Document 2.2.1', 1, 0, 0);


-- Drop table content if it exists
DROP TABLE IF EXISTS content;
-- Create document content table
CREATE TABLE content (
    id BIGINT NOT NULL, -- Document ID
    content TEXT NOT NULL, -- Content
    PRIMARY KEY (id)
);

comment on table content is 'Document content';
comment on column content.id is 'Document ID';
comment on column content.content is 'Content';


-- Drop table user if it exists
DROP TABLE IF EXISTS "user";
-- Create user table
CREATE TABLE "user" (
    id BIGINT NOT NULL, -- ID
    login_name VARCHAR(50) NOT NULL, -- Login Name
    name VARCHAR(50), -- Nickname
    password CHAR(32) NOT NULL, -- Password
    PRIMARY KEY (id),
    UNIQUE (login_name)
);

-- Add comments
comment on table "user" is 'User';
comment on column "user".id is 'ID';
comment on column "user".login_name is 'Login name';
comment on column "user".name is 'Nickname';
comment on column "user".password is 'Password';

-- Insert sample data into user
INSERT INTO "user" (id, login_name, name, password) VALUES
    (1, 'test', 'Test User', 'e70e2222a9d67c4f2eae107533359aa4');

-- Drop table ebook_snapshot if it exists
DROP TABLE IF EXISTS ebook_snapshot;
-- Create ebook_snapshot table
CREATE TABLE ebook_snapshot (
                                id BIGSERIAL NOT NULL, -- ID
                                ebook_id BIGINT NOT NULL DEFAULT 0, -- Ebook ID
                                date DATE NOT NULL, -- Snapshot Date
                                view_count INT NOT NULL DEFAULT 0, -- View Count
                                vote_count INT NOT NULL DEFAULT 0, -- Vote Count
                                view_increase INT NOT NULL DEFAULT 0, -- View Increase
                                vote_increase INT NOT NULL DEFAULT 0, -- Vote Increase
                                PRIMARY KEY (id),
                                UNIQUE (ebook_id, date)
);

