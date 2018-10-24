DROP TABLE IF EXISTS Movie;

 CREATE TABLE Movie (
 	id_movie Serial,
 	name VARCHAR(32),
 	summary TEXT,
 	duration INT,
 	data TIMESTAMP,
 	CONSTRAINT movie_pk PRIMARY KEY (id_movie)
 );
       

        