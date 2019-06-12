USE citiessistemiautobuseve;
ALTER TABLE routes
ADD CONSTRAINT unique_check UNIQUE (start_city,end_city,schedule_id);

	
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`) VALUES ('1', 'Vegim', 'Bytyqi', 'vegimbytyqi', '12345678', '1');
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`) VALUES ('2', 'Visar ', 'Buza', 'visarbuza', '12345678', '1');
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`) VALUES ('3', 'Shpat', 'Gashi', 'shpatgashi', '12345678', '1');
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`) VALUES ('4', 'Korab', 'Spahija', 'korabspahija', '12345678', '1');
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`) VALUES ('5', 'Vigan', 'Dika', 'vigandika', '12345678', '1');
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`) VALUES ('6', 'Diella', 'Ahmetaj', 'diellaahmetaj', '12345678', '2');
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`) VALUES ('7', 'Roza', 'Rrustemi', 'rozarrustemi', '12345678', '2');
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`) VALUES ('8', 'Besim', 'Krasniqi', 'besimkrasniqi', '12345678', '2');

INSERT INTO `sistemiautobuseve`.`companies` (`id`, `name`, `no_buses`, `manager_id`) VALUES ('1', 'Mendi Tours', '35', '6');
INSERT INTO `sistemiautobuseve`.`companies` (`id`, `name`, `no_buses`, `manager_id`) VALUES ('2', 'Arbëria Turist', '12', '7');
INSERT INTO `sistemiautobuseve`.`companies` (`id`, `name`, `no_buses`, `manager_id`) VALUES ('3', 'Drini Reisen', '10', '8');

INSERT INTO `sistemiautobuseve`.`routes` (`id`, `price`, `company_id`, `start_city`, `end_city`, `schedule_id`) VALUES ('1', '4', '1', '1', '4', '4');
INSERT INTO `sistemiautobuseve`.`routes` (`id`, `price`, `company_id`, `start_city`, `end_city`, `schedule_id`) VALUES ('2', '5', '1', '7', '2', '8');

ALTER TABLE users 
add column email VARCHAR(30) UNIQUE;
UPDATE `sistemiautobuseve`.`users` SET `email` = 'vbytyqi@gmail.com' WHERE (`id` = '1');
UPDATE `sistemiautobuseve`.`users` SET `email` = 'vbuza@gmail.com' WHERE (`id` = '2');
UPDATE `sistemiautobuseve`.`users` SET `email` = 'shgashi@gmail.com' WHERE (`id` = '3');
UPDATE `sistemiautobuseve`.`users` SET `email` = 'kspahija@gmail.com' WHERE (`id` = '4');
UPDATE `sistemiautobuseve`.`users` SET `email` = 'vdika@gmail.com' WHERE (`id` = '5');
UPDATE `sistemiautobuseve`.`users` SET `email` = 'dahmetaj@gmail.com' WHERE (`id` = '6');
UPDATE `sistemiautobuseve`.`users` SET `email` = 'rrusta@gmail.com' WHERE (`id` = '7');
UPDATE `sistemiautobuseve`.`users` SET `email` = 'bkrasniqi@gmail.com' WHERE (`id` = '8');
INSERT INTO `sistemiautobuseve`.`users` (`id`, `firstname`, `lastname`, `username`, `password`, `role_id`, `email`) VALUES ('9', 'Rasim', 'Qerimi', 'rasimqerimi', '12345678', '3', 'rqerimi@gmail.com');
