CREATE TABLE COMPANY (
 ID BIGINT PRIMARY KEY AUTO_INCREMENT,
 CNPJ VARCHAR(18) NOT NULL,
 NAME VARCHAR(50) UNIQUE,
 ORIGIN VARCHAR(14)
)engine=InnoDB;