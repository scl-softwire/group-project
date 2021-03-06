package org.softwire.training.slideshowbob.services;

import org.softwire.training.slideshowbob.models.database.Image;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        try {
            jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM upload_images WHERE id = :id")
                    .bind("id", id)
                    .execute()
            );
        } catch (Exception e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new RuntimeException("You cannot delete an image that is already part of a slideshow!");
            } else {
                throw new RuntimeException("Something went wrong deleting this image!", e);
            }
        }
    }


    public void editImage(Image image) {
        jdbi.useHandle(handle -> handle.createUpdate(
                "UPDATE upload_images " +
                        "SET date_time_stamp = :timeStamp, " +
                        "image_name = :imageName, " +
                        "author = :author, " +
                        "license = :license, " +
                        "url = :url " +
                        "WHERE id = :id")
                .bind("id", image.getId())
                .bind("timeStamp", LocalDateTime.now())
                .bind("imageName", image.getImageName())
                .bind("author", image.getAuthor())
                .bind("license", image.getLicense())
                .bind("url", image.getUrl())
                .execute()
        );
    }

    public List<Image> getAllImages() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM upload_images ")
                        .mapToBean(Image.class)
                        .list()
        );
    }


    public Optional<Image> getSingleImage(int id) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM upload_images WHERE id = :id")
                .bind("id", id)
                .mapToBean(Image.class)
                .findFirst()
        );
    }

    public List<String> getImageURLs() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT url FROM upload_images")
                        .mapTo(String.class)
                        .list()
        );
    }
}
