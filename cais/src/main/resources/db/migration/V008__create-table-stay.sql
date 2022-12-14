CREATE TABLE STAY (
 ID BIGINT PRIMARY KEY AUTO_INCREMENT,
 SHIP_ID BIGINT,
 ENTRY TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
 EXIT_SHIP TIMESTAMP,
 FINAL_PRICE DECIMAL(10,2),
 STATUS VARCHAR(6)
)engine=InnoDB;

ALTER TABLE STAY ADD CONSTRAINT FK_SHIP_STAY FOREIGN KEY(SHIP_ID) REFERENCES SHIP (ID);