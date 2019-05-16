CREATE TABLE `sistemiautobuseve`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `sistemiautobuseve`.`cities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `sistemiautobuseve`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `sistemiautobuseve`.`roles` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION);
    
    
CREATE TABLE `sistemiautobuseve`.`timetable` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIME NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `sistemiautobuseve`.`companies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `no_buses` INT NULL,
  `manager_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `manager_id_idx` (`manager_id` ASC) VISIBLE,
  CONSTRAINT `manager_id`
    FOREIGN KEY (`manager_id`)
    REFERENCES `sistemiautobuseve`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `sistemiautobuseve`.`routes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL,
  `company_id` INT NULL,
  `start_city` INT NULL,
  `end_city` INT NULL,
  `schedule_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,
  INDEX `start_city_idx` (`start_city` ASC) VISIBLE,
  INDEX `end_city_idx` (`end_city` ASC) VISIBLE,
  INDEX `schedule_id_idx` (`schedule_id` ASC) VISIBLE,
  CONSTRAINT `company_id`
    FOREIGN KEY (`company_id`)
    REFERENCES `sistemiautobuseve`.`companies` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `start_city`
    FOREIGN KEY (`start_city`)
    REFERENCES `sistemiautobuseve`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `end_city`
    FOREIGN KEY (`end_city`)
    REFERENCES `sistemiautobuseve`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `schedule_id`
    FOREIGN KEY (`schedule_id`)
    REFERENCES `sistemiautobuseve`.`timetable` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
