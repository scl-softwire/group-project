CREATE TABLE upload_images (
	id int(11) NOT NULL AUTO_INCREMENT,
    date_time_stamp TIMESTAMP,
    image_name VARCHAR(50),
    author VARCHAR(50),
    license VARCHAR(50),
    url VARCHAR(2000),
    PRIMARY KEY (id)
    );