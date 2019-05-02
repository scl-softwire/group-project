CREATE TABLE `slideshows`
(
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `author_id` int
);

CREATE TABLE `slideshow_slides`
(
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `slideshow_id` int,
  `slide_id` int,
  `order` int
);

ALTER TABLE `upload_images` MODIFY COLUMN `author` int;

ALTER TABLE `upload_images` ADD FOREIGN KEY (`author`) REFERENCES `admins` (`id`);

ALTER TABLE `slideshows` ADD FOREIGN KEY (`author_id`) REFERENCES `admins` (`id`);

ALTER TABLE `slideshow_slides` ADD FOREIGN KEY (`slideshow_id`) REFERENCES `slideshows` (`id`);

ALTER TABLE `slideshow_slides` ADD FOREIGN KEY (`slide_id`) REFERENCES `upload_images` (`id`);

