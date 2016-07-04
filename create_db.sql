CREATE USER musiccatalog@localhost identified BY '1234';
GRANT usage ON *.* TO musiccatalog@localhost identified BY '1234';
CREATE DATABASE IF NOT EXISTS mc;
GRANT ALL privileges ON mc.* TO musiccatalog@localhost;
USE mc;
CREATE TABLE SONGS(
    ID INT NOT NULL auto_increment, 
    NAME VARCHAR(50) NOT NULL,
    ALBUMID INT NOT NULL,
    ARTISTID INT NOT NULL,
    USERID INT NOT NULL,
    SOURCE VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);

select * from Songs