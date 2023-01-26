-- -----------------------------------------------------
-- Schema training
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `training` DEFAULT CHARACTER SET utf8 ;
USE `training` ;

-- -----------------------------------------------------
-- Table `training`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training`.`User` (
  `user_id` INT NOT NULL,
  `username` VARCHAR(500) NULL,
  PRIMARY KEY (`user_id`));

-- -----------------------------------------------------
-- Table `training`.`Training_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training`.`Training_details` (
  `user_training_id` INT NOT NULL,
  `training_id` INT NULL,
  `training_date` DATE NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`user_training_id`, `user_id`),
  INDEX `fk_Training_details_User_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Training_details_User`
    FOREIGN KEY (`user_id`)
    REFERENCES `training`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `training`.`data`
-- -----------------------------------------------------
INSERT INTO `training`.`User` (`user_id`, `username`)
VALUES (1, "John Doe"),
(2, "Jane Don"),
(3, "Alice Jones"),
(4, "Lisa Romero");


INSERT INTO `training`.`Training_details` (`user_training_id`, `user_id`, `training_id`, `training_date`)
VALUES (1, 1, 1, "2015-08-02"),
(2, 2, 1, "2015-08-03"),
(3, 3, 2, "2015-08-02"),
(4, 4, 2, "2015-08-04"),
(5, 2, 2, "2015-08-03"),
(6, 1, 1, "2015-08-02"),
(7, 3, 2, "2015-08-04"),
(8, 4, 3, "2015-08-03"),
(9, 1, 4, "2015-08-03"),
(10, 3, 1, "2015-08-02"),
(11, 4, 2, "2015-08-04"),
(12, 3, 2, "2015-08-02"),
(13, 1, 1, "2015-08-02"),
(14, 4, 3, "2015-08-03");