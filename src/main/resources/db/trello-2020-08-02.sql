USE `trello`;

ALTER TABLE `trello`.`checklist` ADD COLUMN `checked` TINYINT NOT NULL DEFAULT 0 AFTER `item`;