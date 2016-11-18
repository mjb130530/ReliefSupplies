CREATE SCHEMA IF NOT EXISTS JAMUsers DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS JAMUsers.logins(
	JAMuserID BIGINT NOT NULL AUTO_INCREMENT,
	JAMusername VARCHAR(50),
	JAMpassword VARCHAR(32),
    JAMemail VARCHAR(50),
    JAMphone VARCHAR(10),
	JAMpermission BIGINT,
	PRIMARY KEY(JAMuserID));
    
    