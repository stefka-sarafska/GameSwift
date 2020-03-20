-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema battle
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema battle
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `battle` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `battle` ;

-- -----------------------------------------------------
-- Table `battle`.`batlle_information`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `battle`.`batlle_information` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `people_number` INT NOT NULL,
  `trolls_number` INT NOT NULL,
  `people_points` INT NULL DEFAULT '0',
  `trolls_points` INT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `battle`.`people`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `battle`.`people` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `person_name` VARCHAR(20) NULL DEFAULT NULL,
  `person_strength` INT NOT NULL,
  `person_resistance` INT NOT NULL,
  PRIMARY KEY (`person_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `battle`.`trolls`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `battle`.`trolls` (
  `troll_id` INT NOT NULL AUTO_INCREMENT,
  `troll_name` VARCHAR(20) NULL DEFAULT NULL,
  `troll_strength` INT NOT NULL,
  `troll_resistance` INT NOT NULL,
  PRIMARY KEY (`troll_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
