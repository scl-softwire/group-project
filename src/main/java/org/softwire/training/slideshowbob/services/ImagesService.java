package org.softwire.training.slideshowbob.services;

import org.softwire.training.slideshowbob.models.database.Image;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImagesService extends DatabaseService {

    public void uploadImage(Image image) {
        jdbi.useHandle(handle -> handle.createUpdate(
                "INSERT INTO upload_images " +
                    "(date_time_stamp, image_name, author, license, url) " +
                    "VALUES (:timestamp, :imageName, :author, :license, :url)")
                .bind("timestamp", LocalDateTime.now())
                .bind("imageName", image.getImageName())
                .bind("author", image.getAuthor())
                .bind("license", image.getLicense())
                .bind("url", image.getUrl())
                .execute()
        );
    }

        public void deleteImage(int id) {
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM upload_images WHERE id = :id")
                .bind("id", id)
                .execute()
        );
    }

        public List<Image> getAllImages () {
            return jdbi.withHandle(handle ->
                    handle.createQuery("SELECT * FROM upload_images ")
                    .mapToBean(Image.class)
                    .list()
                    );
        }


}
