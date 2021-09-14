DROP TABLE IF EXISTS carro;

CREATE TABLE Carro
(
 brand varchar(50) NOT NULL,
 model varchar(50) NOT NULL,
 "plate" varchar(7) NOT NULL,
 CONSTRAINT PK_34 PRIMARY KEY ( "plate" )
);

DROP TABLE IF EXISTS checkIn;

CREATE TABLE CheckIn
(
 "id"        integer NOT NULL,
 id_carro  varchar(7) NOT NULL,
 inicio    date NOT NULL,
 pagamento boolean NOT NULL,
 CheckOut  date ,
 CONSTRAINT PK_41 PRIMARY KEY ( "id" ),
 CONSTRAINT FK_21 FOREIGN KEY ( placa ) REFERENCES Carro ( "plate" )
);

CREATE INDEX fkIdx_26 ON CheckIn
(
 id_carro
);









