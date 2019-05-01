package org.softwire.training.slideshowbob.services;

import org.softwire.training.slideshowbob.models.database.Image;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ImagesService extends DatabaseService {

    public void uploadImage(Image image) {
        jdbi.useHandle(handle -> handle.createUpdate(
                "INSERT INTO upload_images " +
                "(date_time_stamp, image_name, author, license, url) " +
                "VALUES (:timestamp, :imageName, : :author, :license, :url)")
                .bind("timestamp", LocalDateTime.now())
                .bind("imageName", image.getImageName())
                .bind("author", image.getAuthor())
                .bind("license", image.getLicense())
                .bind("url", image.getUrl())
                .execute()
        );
    }



}
