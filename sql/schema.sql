-- MySQL Script generated by MySQL Workbench
-- Tue May 10 09:26:30 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema chat_app
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `chat_app` ;

-- -----------------------------------------------------
-- Schema chat_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chat_app` DEFAULT CHARACTER SET utf8 ;
USE `chat_app` ;

-- -----------------------------------------------------
-- Table `chat_app`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat_app`.`user` ;

CREATE TABLE IF NOT EXISTS `chat_app`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(250) NOT NULL,
  `password_hash` VARCHAR(250) NOT NULL,
  `disabled` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat_app`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat_app`.`room` ;

CREATE TABLE IF NOT EXISTS `chat_app`.`room` (
  `room_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`room_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat_app`.`room_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat_app`.`room_has_user` ;

CREATE TABLE IF NOT EXISTS `chat_app`.`room_has_user` (
  `room_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`room_id`, `user_id`),
  INDEX `fk_room_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_room_has_user_room_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `fk_room_has_user_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `chat_app`.`room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `chat_app`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat_app`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat_app`.`message` ;

CREATE TABLE IF NOT EXISTS `chat_app`.`message` (
  `message_id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(250) NOT NULL,
  `timestamp` TIMESTAMP(6) NOT NULL,
  `room_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `username` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`message_id`),
  INDEX `fk_message_room1_idx` (`room_id` ASC) VISIBLE,
  INDEX `fk_message_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_message_room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `chat_app`.`room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `chat_app`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat_app`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat_app`.`role` ;

CREATE TABLE IF NOT EXISTS `chat_app`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat_app`.`role_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat_app`.`role_has_user` ;

CREATE TABLE IF NOT EXISTS `chat_app`.`role_has_user` (
  `role_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`role_id`, `user_id`),
  INDEX `fk_role_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_role_has_user_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_role_has_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `chat_app`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `chat_app`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat_app`.`token`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat_app`.`token` ;

CREATE TABLE IF NOT EXISTS `chat_app`.`token` (
  `token` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`token`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;










delimiter //
create procedure set_known_good_state()
begin

	delete from room_has_user;
	delete from role_has_user;
    
    delete from message;
    alter table message auto_increment = 1;
    
    delete from room_has_user;
    
	delete from `user`;
    alter table `user` auto_increment = 1;
    delete from `role`;
    alter table `role` auto_increment = 1;
    
    delete from room;
    alter table room auto_increment = 1;
    
    insert into role(`name`) values("USER");
    insert into role(`name`) values("ADMIN");
    
    insert into user(user_id, username, password_hash, disabled) values(1, "nik", "$2a$10$yxFMZBgKlAMaOR7qwIFMmuvvmi.w0EgEV0AnTgV/mdVzJi.TEofOG", 0);
    insert into user(user_id, username, password_hash, disabled) values(2, "shiloh", "$2a$10$yxFMZBgKlAMaOR7qwIFMmuvvmi.w0EgEV0AnTgV/mdVzJi.TEofOG", 0);
    insert into user(user_id, username, password_hash, disabled) values(3, "becky", "$2a$10$yxFMZBgKlAMaOR7qwIFMmuvvmi.w0EgEV0AnTgV/mdVzJi.TEofOG", 0);
    insert into user(user_id, username, password_hash, disabled) values(4, "sam", "$2a$10$yxFMZBgKlAMaOR7qwIFMmuvvmi.w0EgEV0AnTgV/mdVzJi.TEofOG", 0);

    insert into role_has_user(role_id, user_id) values(2, 1);
    insert into role_has_user(role_id, user_id) values(2, 2);
    insert into role_has_user(role_id, user_id) values(2, 3);
    insert into role_has_user(role_id, user_id) values(2, 4);
    
    insert into room(room_id, `name`) values(1, "Main");
    insert into room(room_id, `name`) values(2, "test room 2");
    insert into room(room_id, `name`) values(3, "test room 3");
    insert into room(room_id, `name`) values(4, "test room 4");
    
    insert into room_has_user(room_id, user_id) values(1, 1);
	insert into room_has_user(room_id, user_id) values(2, 1);
	insert into room_has_user(room_id, user_id) values(3, 1);
    
    insert into message(message, `timestamp`, room_id, user_id, username) values("test", "2022-05-03 12:12:12", 1, 1, "nik");
    insert into message(message, `timestamp`, room_id, user_id, username) values("test2", "2022-05-03 11:12:12", 1, 1, "nik");
    insert into message(message, `timestamp`, room_id, user_id, username) values("test3", "2022-05-03 10:12:12", 1, 1, "nik");
    
end //
delimiter ;
