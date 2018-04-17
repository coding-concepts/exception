-- ---------------------------------------------------------------------------
--      USER TABLE. STORES THE DETAILS OF A USER
-- ---------------------------------------------------------------------------

CREATE TABLE USER (
ID BIGINT NOT NULL  AUTO_INCREMENT,
EMAIL  VARCHAR(255) NOT NULL ,
FIRST_NAME  VARCHAR(255) NOT NULL ,
LAST_NAME  VARCHAR(255) NOT NULL ,
GENDER CHAR(1) NOT NULL ,
PHONE CHAR(10) NOT NULL ,
DOB DATE NOT NULL ,
PASSWORD VARCHAR(255)NOT NULL ,
SALT  VARCHAR(255) ,
PRIMARY KEY (ID),
UNIQUE (EMAIL)
);

