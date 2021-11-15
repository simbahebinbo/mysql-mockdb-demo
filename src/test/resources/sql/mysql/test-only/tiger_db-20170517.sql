CREATE DATABASE IF NOT EXISTS tiger_db;
USE tiger_db;

DROP TABLE IF EXISTS t_emails;
CREATE TABLE t_emails
(
    id    int(11)      NOT NULL AUTO_INCREMENT,
    email varchar(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

INSERT INTO t_emails
VALUES (1, '111@aaa.com'),
       (2, '222@bbb.com');


