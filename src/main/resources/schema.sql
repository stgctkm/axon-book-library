DROP TABLE IF EXISTS token_entry;
CREATE TABLE IF NOT EXISTS token_entry
(
    segment INTEGER NOT NULL,
    processor_name varchar(255) NOT NULL,
    token bigint,
    token_type varchar(255),
    timestamp VARCHAR(1000),
    owner VARCHAR(1000),
    PRIMARY KEY (segment, processor_name)
    );
DELETE
FROM token_entry;