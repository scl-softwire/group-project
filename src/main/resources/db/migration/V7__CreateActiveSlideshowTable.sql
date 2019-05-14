CREATE TABLE `active-slideshow` (
`restriction` ENUM('') NOT NULL PRIMARY KEY,
`id` int
);

ALTER TABLE `active-slideshow` ADD FOREIGN KEY (`id`) REFERENCES `slideshows` (`id`);

INSERT INTO `active-slideshow` (restriction) VALUES ('');
