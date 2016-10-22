CREATE SCHEMA `tasker` DEFAULT CHARACTER SET utf8 ;

CREATE  TABLE IF NOT EXISTS `tasker`. `tasks` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY  ,
  `name` VARCHAR(255) DEFAULT 'task',
  `estimation_time` DATE DEFAULT '2012-12-31',
  `priority` VARCHAR(255) DEFAULT 'low',
  `done` BOOL DEFAULT FALSE
);