CREATE DATABASE Order_Management_Database;

USE Order_Management_Database;

CREATE TABLE otype(
tid INT PRIMARY KEY AUTO_INCREMENT,
tname VARCHAR(35) NOT NULL
);
INSERT INTO otype(tname)
VALUES
('普通订单'),
('OTC送药订单'),
('果蔬订单');

CREATE TABLE orders(
oid INT PRIMARY KEY AUTO_INCREMENT,
uname VARCHAR(35) NOT NULL,
tel VARCHAR(40) NOT NULL,
address VARCHAR(100) NOT NULL,
payway INT NOT NULL,
sender VARCHAR(25) NOT NULL,
phone VARCHAR(40) NOT NULL,
idcard VARCHAR(40) NOT NULL,
overtime DATE,
isover INT NOT NULL DEFAULT 1,
tid INT NOT NULL,
FOREIGN KEY(tid) REFERENCES otype(tid)
);