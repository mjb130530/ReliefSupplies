CREATE SCHEMA IF NOT EXISTS DisasterRelief DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS DisasterRelief.Locations(
	locationID BIGINT NOT NULL,
	locationState VARCHAR(15),
	county VARCHAR(30),
	city VARCHAR(30),
	street VARCHAR(30),
	zipcode VARCHAR(15),
	cityTaxes BIGINT,
	stateTaxes BIGINT,
	rent BIGINT,
	electricity BIGINT,
	water BIGINT,
	PRIMARY KEY(locationID));

CREATE TABLE IF NOT EXISTS DisasterRelief.Supplies(
	locationID BIGINT NOT NULL,
	supplyName VARCHAR(30),
	supplyType VARCHAR(30),
	supplyQuantity VARCHAR(30),
	supplyDescription VARCHAR(60),
	FOREIGN KEY(locationID) REFERENCES DisasterRelief.Locations (locationID));

CREATE TABLE IF NOT EXISTS DisasterRelief.Employees(
	employeeID BIGINT NOT NULL AUTO_INCREMENT,
	locationID BIGINT NOT NULL,
	employeeFirst VARCHAR(30),
	employeeLast VARCHAR(30),
	employeeSSN VARCHAR(64),
	employeeType VARCHAR(30),
	dateOfHire VARCHAR(30),
	PRIMARY KEY(employeeID),
	FOREIGN KEY(locationID) REFERENCES DisasterRelief.Locations (locationID));

CREATE TABLE IF NOT EXISTS DisasterRelief.Donors(
	donorID BIGINT AUTO_INCREMENT,
	locationID BIGINT NOT NULL,
	donorFirst VARCHAR(30),
	donorLast VARCHAR(30),
	donationDate VARCHAR(30),
	donationType VARCHAR(30),
	donationValue VARCHAR(15),
	donationDescription VARCHAR(60),
	PRIMARY KEY(donorID),
	FOREIGN KEY(locationID) REFERENCES DisasterRelief.Locations (locationID));

