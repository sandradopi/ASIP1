DROP TABLE IF EXISTS movie;

CREATE TABLE movie (
idMovie Serial,
name VARCHAR (32),
summary TEXT,
duration INT,
data DATE,
CONSTRAINT movie_pk PRIMARY KEY (idMovie)
);

       

        