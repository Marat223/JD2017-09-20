-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ali
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ali` ;

-- -----------------------------------------------------
-- Schema ali
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ali` DEFAULT CHARACTER SET utf8 ;
USE `ali` ;

-- -----------------------------------------------------
-- Table `ali`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ali`.`roles` ;

CREATE TABLE IF NOT EXISTS `ali`.`roles` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ali`.`roles_additional`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ali`.`roles_additional` ;

CREATE TABLE IF NOT EXISTS `ali`.`roles_additional` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ali`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ali`.`users` ;

CREATE TABLE IF NOT EXISTS `ali`.`users` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Login` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `FKroles` INT NOT NULL,
  `FKroles_additional` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_users_roles_idx` (`FKroles` ASC),
  INDEX `fk_users_roles_additional1_idx` (`FKroles_additional` ASC),
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`FKroles`)
    REFERENCES `ali`.`roles` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_users_roles_additional1`
    FOREIGN KEY (`FKroles_additional`)
    REFERENCES `ali`.`roles_additional` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ali`.`tickets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ali`.`tickets` ;

CREATE TABLE IF NOT EXISTS `ali`.`tickets` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Destination` VARCHAR(45) NOT NULL,
  `Company` VARCHAR(45) NOT NULL,
  `Date` DATE NOT NULL,
  `Price` DOUBLE NOT NULL,
  `FKusers` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_tickets_users1_idx` (`FKusers` ASC),
  CONSTRAINT `fk_tickets_users1`
    FOREIGN KEY (`FKusers`)
    REFERENCES `ali`.`users` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `ali`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `ali`;
INSERT INTO `ali`.`roles` (`ID`, `Role`) VALUES (DEFAULT, 'Administrator');
INSERT INTO `ali`.`roles` (`ID`, `Role`) VALUES (DEFAULT, 'User');
INSERT INTO `ali`.`roles` (`ID`, `Role`) VALUES (DEFAULT, 'Guest');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ali`.`roles_additional`
-- -----------------------------------------------------
START TRANSACTION;
USE `ali`;
INSERT INTO `ali`.`roles_additional` (`ID`, `Role`) VALUES (DEFAULT, 'User');
INSERT INTO `ali`.`roles_additional` (`ID`, `Role`) VALUES (DEFAULT, 'Premium User');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ali`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `ali`;
INSERT INTO `ali`.`users` (`ID`, `Login`, `Password`, `Email`, `FKroles`, `FKroles_additional`) VALUES (DEFAULT, 'admin', 'admin', 'admin@fly.by', 1, NULL);
INSERT INTO `ali`.`users` (`ID`, `Login`, `Password`, `Email`, `FKroles`, `FKroles_additional`) VALUES (DEFAULT, 'user', 'user', 'user@fly.by', 2, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ali`.`tickets`
-- -----------------------------------------------------
START TRANSACTION;
USE `ali`;
INSERT INTO `ali`.`tickets` (`ID`, `Destination`, `Company`, `Date`, `Price`, `FKusers`) VALUES (DEFAULT, 'Moskow', 'Belavia', '29.11.17', 160, 2);

COMMIT;

