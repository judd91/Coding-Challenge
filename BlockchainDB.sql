CREATE database blockchainDB;
DROP TABLE IF EXISTS Payments;
USE blockchainDB;
CREATE TABLE Payments(
  id int NOT NULL AUTO_INCREMENT,
  paymentId varchar(45) DEFAULT NULL,
  success bool DEFAULT FALSE,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;