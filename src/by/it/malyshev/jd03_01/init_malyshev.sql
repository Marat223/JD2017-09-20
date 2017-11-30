-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema malyshev
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `malyshev` ;

-- -----------------------------------------------------
-- Schema malyshev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `malyshev` DEFAULT CHARACTER SET utf8 ;
USE `malyshev` ;

-- -----------------------------------------------------
-- Table `malyshev`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `malyshev`.`roles` ;

CREATE TABLE IF NOT EXISTS `malyshev`.`roles` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `malyshev`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `malyshev`.`users` ;

CREATE TABLE IF NOT EXISTS `malyshev`.`users` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(100) NULL,
  `password` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `roles_ID` INT NOT NULL DEFAULT 3,
  PRIMARY KEY (`ID`),
  INDEX `fk_users_role_idx` (`roles_ID` ASC),
  CONSTRAINT `fk_users_role`
    FOREIGN KEY (`roles_ID`)
    REFERENCES `malyshev`.`roles` (`ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `malyshev`.`books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `malyshev`.`books` ;

CREATE TABLE IF NOT EXISTS `malyshev`.`books` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `pubyear` YEAR NOT NULL,
  `isbn` VARCHAR(45) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `malyshev`.`messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `malyshev`.`messages` ;

CREATE TABLE IF NOT EXISTS `malyshev`.`messages` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(10000) NOT NULL,
  `users_ID` INT NOT NULL,
  `books_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_messages_users1_idx` (`users_ID` ASC),
  INDEX `fk_messages_books1_idx` (`books_ID` ASC),
  CONSTRAINT `fk_messages_users1`
    FOREIGN KEY (`users_ID`)
    REFERENCES `malyshev`.`users` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_messages_books1`
    FOREIGN KEY (`books_ID`)
    REFERENCES `malyshev`.`books` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `malyshev`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `malyshev`.`orders` ;

CREATE TABLE IF NOT EXISTS `malyshev`.`orders` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `users_ID` INT NOT NULL,
  `books_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_order_users1_idx` (`users_ID` ASC),
  INDEX `fk_orders_books1_idx` (`books_ID` ASC),
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_ID`)
    REFERENCES `malyshev`.`users` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_books1`
    FOREIGN KEY (`books_ID`)
    REFERENCES `malyshev`.`books` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `malyshev`.`store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `malyshev`.`store` ;

CREATE TABLE IF NOT EXISTS `malyshev`.`store` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NULL,
  `books_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_store_books1_idx` (`books_ID` ASC),
  CONSTRAINT `fk_store_books1`
    FOREIGN KEY (`books_ID`)
    REFERENCES `malyshev`.`books` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `malyshev`.`authors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `malyshev`.`authors` ;

CREATE TABLE IF NOT EXISTS `malyshev`.`authors` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `malyshev`.`authors_books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `malyshev`.`authors_books` ;

CREATE TABLE IF NOT EXISTS `malyshev`.`authors_books` (
  `authors_ID` INT NOT NULL,
  `books_ID` INT NOT NULL,
  PRIMARY KEY (`authors_ID`, `books_ID`),
  INDEX `fk_Authors_has_books_books1_idx` (`books_ID` ASC),
  INDEX `fk_Authors_has_books_Authors1_idx` (`authors_ID` ASC),
  CONSTRAINT `fk_Authors_has_books_Authors1`
    FOREIGN KEY (`authors_ID`)
    REFERENCES `malyshev`.`authors` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Authors_has_books_books1`
    FOREIGN KEY (`books_ID`)
    REFERENCES `malyshev`.`books` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `malyshev`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `malyshev`;
INSERT INTO `malyshev`.`roles` (`ID`, `role`) VALUES (1, 'Administrator');
INSERT INTO `malyshev`.`roles` (`ID`, `role`) VALUES (2, 'Guest');

COMMIT;


-- -----------------------------------------------------
-- Data for table `malyshev`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `malyshev`;
INSERT INTO `malyshev`.`users` (`ID`, `login`, `password`, `email`, `roles_ID`) VALUES (1, 'admin', 'padmin', 'admin@it.by', 1);
INSERT INTO `malyshev`.`users` (`ID`, `login`, `password`, `email`, `roles_ID`) VALUES (2, 'user', 'puser', 'user@it.by', 2);
INSERT INTO `malyshev`.`users` (`ID`, `login`, `password`, `email`, `roles_ID`) VALUES (3, 'user2', 'puser2', 'user2@it.by', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `malyshev`.`books`
-- -----------------------------------------------------
START TRANSACTION;
USE `malyshev`;
INSERT INTO `malyshev`.`books` (`ID`, `title`, `pubyear`, `isbn`, `price`) VALUES (1, 'Java 8. Полное руководство', 2015, '978-5-8459-1918-2', 179.0);
INSERT INTO `malyshev`.`books` (`ID`, `title`, `pubyear`, `isbn`, `price`) VALUES (2, 'Сильмариллион', 1977, '978-5-17-083893-6', 47.75);

COMMIT;


-- -----------------------------------------------------
-- Data for table `malyshev`.`messages`
-- -----------------------------------------------------
START TRANSACTION;
USE `malyshev`;
INSERT INTO `malyshev`.`messages` (`ID`, `message`, `users_ID`, `books_ID`) VALUES (1, 'Хорошая книга', 2, 1);
INSERT INTO `malyshev`.`messages` (`ID`, `message`, `users_ID`, `books_ID`) VALUES (2, 'Скучная книга', 3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `malyshev`.`orders`
-- -----------------------------------------------------
START TRANSACTION;
USE `malyshev`;
INSERT INTO `malyshev`.`orders` (`ID`, `quantity`, `users_ID`, `books_ID`) VALUES (1, 1, 1, 1);
INSERT INTO `malyshev`.`orders` (`ID`, `quantity`, `users_ID`, `books_ID`) VALUES (2, 2, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `malyshev`.`store`
-- -----------------------------------------------------
START TRANSACTION;
USE `malyshev`;
INSERT INTO `malyshev`.`store` (`ID`, `quantity`, `books_ID`) VALUES (1, 15, 1);
INSERT INTO `malyshev`.`store` (`ID`, `quantity`, `books_ID`) VALUES (2, 3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `malyshev`.`authors`
-- -----------------------------------------------------
START TRANSACTION;
USE `malyshev`;
INSERT INTO `malyshev`.`authors` (`ID`, `name`) VALUES (1, 'Герберт Шилдт');
INSERT INTO `malyshev`.`authors` (`ID`, `name`) VALUES (2, 'Дж. Р. Р. Толкин');

COMMIT;


-- -----------------------------------------------------
-- Data for table `malyshev`.`authors_books`
-- -----------------------------------------------------
START TRANSACTION;
USE `malyshev`;
INSERT INTO `malyshev`.`authors_books` (`authors_ID`, `books_ID`) VALUES (1, 1);
INSERT INTO `malyshev`.`authors_books` (`authors_ID`, `books_ID`) VALUES (2, 2);

COMMIT;

