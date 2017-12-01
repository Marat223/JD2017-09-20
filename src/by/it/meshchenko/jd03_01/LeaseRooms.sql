-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema meshchenko
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `meshchenko` ;

-- -----------------------------------------------------
-- Schema meshchenko
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `meshchenko` DEFAULT CHARACTER SET utf8 ;
USE `meshchenko` ;

-- -----------------------------------------------------
-- Table `meshchenko`.`addr_countries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`addr_countries` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`addr_countries` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meshchenko`.`addr_cities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`addr_cities` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`addr_cities` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `CounrtyId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Name_CountryId_UNIQUE` (`Name` ASC, `CounrtyId` ASC),
  INDEX `FK_Countries_Cities_idx` (`CounrtyId` ASC),
  CONSTRAINT `FK_Countries_Cities`
    FOREIGN KEY (`CounrtyId`)
    REFERENCES `meshchenko`.`addr_countries` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meshchenko`.`addr_streets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`addr_streets` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`addr_streets` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `CityId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Name_CityId_UNIQUE` (`Name` ASC, `CityId` ASC),
  INDEX `FK_Cities_Streets_idx` (`CityId` ASC),
  CONSTRAINT `FK_Cities_Streets`
    FOREIGN KEY (`CityId`)
    REFERENCES `meshchenko`.`addr_cities` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meshchenko`.`addr_addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`addr_addresses` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`addr_addresses` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `StreetId` INT(11) NOT NULL,
  `BuildingNumberStr` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `BuildingNumberStr_StreetId_UNIQUE` (`BuildingNumberStr` ASC, `StreetId` ASC),
  INDEX `FK_Streets_Addresses_idx` (`StreetId` ASC),
  CONSTRAINT `FK_Streets_Addresses`
    FOREIGN KEY (`StreetId`)
    REFERENCES `meshchenko`.`addr_streets` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meshchenko`.`crm_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`crm_roles` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`crm_roles` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Role` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Role_UNIQUE` (`Role` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meshchenko`.`crm_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`crm_users` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`crm_users` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(1023) NOT NULL,
  `PhoneNumber` VARCHAR(255) NULL DEFAULT NULL,
  `Email` VARCHAR(255) NOT NULL,
  `EmailConfirmed` bit(1) NOT NULL DEFAULT b'0',
  `RoleId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC),
  INDEX `RoleId` (`RoleId` ASC),
  CONSTRAINT `FK_roles_users`
    FOREIGN KEY (`RoleId`)
    REFERENCES `meshchenko`.`crm_roles` (`Id`)
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meshchenko`.`ls_shoppingcenters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`ls_shoppingcenters` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`ls_shoppingcenters` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `AddressId` INT(11) NOT NULL,
  `Lat` FLOAT NULL DEFAULT NULL,
  `Lng` FLOAT NULL DEFAULT NULL,
  `Description` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Name_AddressId_UNIQUE` (`Name` ASC, `AddressId` ASC),
  INDEX `FK_Addresses_ShoppingCenters_idx` (`AddressId` ASC),
  CONSTRAINT `FK_Addresses_ShoppingCenters`
    FOREIGN KEY (`AddressId`)
    REFERENCES `meshchenko`.`addr_addresses` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meshchenko`.`ls_rooms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`ls_rooms` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`ls_rooms` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `Square` FLOAT NULL DEFAULT NULL,
  `ShoppingCenterId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Name_ShoppingCenterId_UNIQUE` (`Name` ASC, `ShoppingCenterId` ASC),
  INDEX `FK_ShoppingCenters_Rooms_idx` (`ShoppingCenterId` ASC),
  CONSTRAINT `FK_ShoppingCenters_Rooms`
    FOREIGN KEY (`ShoppingCenterId`)
    REFERENCES `meshchenko`.`ls_shoppingcenters` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `meshchenko`.`ls_leaserooms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meshchenko`.`ls_leaserooms` ;

CREATE TABLE IF NOT EXISTS `meshchenko`.`ls_leaserooms` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Price` FLOAT NULL DEFAULT NULL,
  `DateStartLease` DATETIME NULL DEFAULT NULL,
  `DateStopLease` DATETIME NULL DEFAULT NULL,
  `RoomId` INT(11) NOT NULL,
  `UserId` INT(11) NOT NULL COMMENT 'owner of room',
  PRIMARY KEY (`Id`),
  INDEX `FK_Rooms_LeaseRooms_idx` (`RoomId` ASC),
  INDEX `FK_Users_LeaseRooms_idx` (`UserId` ASC),
  CONSTRAINT `FK_Rooms_LeaseRooms`
    FOREIGN KEY (`RoomId`)
    REFERENCES `meshchenko`.`ls_rooms` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_Users_LeaseRooms`
    FOREIGN KEY (`UserId`)
    REFERENCES `meshchenko`.`crm_users` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
