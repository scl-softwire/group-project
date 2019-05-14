package org.softwire.training.slideshowbob.services;

import org.jdbi.v3.core.statement.PreparedBatch;
import org.softwire.training.slideshowbob.models.database.AdminUser;
import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.database.SlideshowSlide;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SlideshowService extends DatabaseService {

    public void createSlideshow(Slideshow slideshow, List<Integer> imagesIds, AdminUser loggedInAs) {
        // Create Slideshow in Slideshow Table

        jdbi.useHandle(handle -> {
            int id = handle.createUpdate("INSERT INTO slideshows " +
                   "(slideshow_name, author_id) VALUES (:slideshowName, :authorId)")
                    .bind("slideshowName", slideshow.getSlideshowName())
                    .bind("authorId", loggedInAs.getId())
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .findOnly();

            PreparedBatch batch = handle.prepareBatch("INSERT INTO slideshow_slides " +
                    "(slideshow_id, slide_id, `order`) VALUES (:slideshow_id, :slide_id, :order)");
            for (int i = 0; i < imagesIds.size(); i++) {
                batch
                        .bind("slideshow_id", id)
                        .bind("slide_id", imagesIds.get(i))
                        .bind("order", i)
                        .add();
            }
            batch.execute();
        });
    }

    public Slideshow getSlideshow(int id) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM slideshows WHERE id = :id")
                .bind("id", id)
                .mapToBean(Slideshow.class)
                .findOne()
                .get()
        );
    }

    public List<SlideshowSlide> getImagesforSlideshow(int id) {
        return jdbi.withHandle(handle -> handle.createQuery(
                "SELECT slideshow_slides.slideshow_id, slideshow_slides.slide_id, slideshow_slides.order, " +
                        "slideshows.id AS sshow_id, slideshows.author_id AS sshow_author_id, " +
                        "upload_images.id AS image_id, " +
                        "upload_images.date_time_stamp AS image_date_time_stamp, " +
                        "upload_images.image_name AS image_image_name, " +
                        "upload_images.author AS image_author, " +
                        "upload_images.license AS image_license, " +
                        "upload_images.url AS image_url " +
                        "FROM slideshow_slides " +
                        "INNER JOIN slideshows ON slideshow_slides.slideshow_id = slideshows.id " +
                        "INNER JOIN upload_images ON slideshow_slides.slide_id = upload_images.id " +
                        "WHERE slideshow_id = :id")
                .bind("id", id)
                .mapToBean(SlideshowSlide.class)
                .list());
    }

    public void deleteSlideshow(int id) {
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM slideshow_slides " +
                "WHERE slideshow_id = :slideshow_id")
                .bind("slideshow_id", id)
                .execute());
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM slideshows " +
                "WHERE id = :id").bind("id", id)
                .execute());
    }

    public void deleteSlide(int id, int slideshowId) {
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM slideshow_slides " +
                "WHERE slide_id = :slide_id AND slideshow_id = :slideshowId")
                .bind("slide_id", id)
                .bind("slideshowId", slideshowId)
                .execute());
    }

    public void uploadSlide(Image image, int slideshowId) {
        jdbi.useHandle(handle -> {
            int id = handle.createUpdate("INSERT INTO upload_images " +
                    "(date_time_stamp, image_name, author, license, url) " +
                    "VALUES (:timestamp, :imageName, :author, :license, :url)")
                    .bind("timestamp", LocalDateTime.now())
                    .bind("imageName", image.getImageName())
                    .bind("author", image.getAuthor())
                    .bind("license", image.getLicense())
                    .bind("url", image.getUrl())
                    .executeAndReturnGeneratedKeys()
                    .mapTo(Integer.class)
                    .findOnly();

            handle.createUpdate("INSERT INTO slideshow_slides " +
                    "(slideshow_id, slide_id) VALUES (:slideshow_id, :slide_id)")
                    .bind("slide_id", id)
                    .bind("slideshow_id", slideshowId)
                    .execute();
        });
    }

    public List<Slideshow> getAllSlideshows() {
        return jdbi.withHandle(handle -> handle.createQuery(
                "SELECT slideshows.id, slideshows.author_id, slideshows.slideshow_name, " +
                        "admins.id AS adminUser_id, " +
                        "admins.username AS adminUser_username FROM slideshows " +
                        "INNER JOIN admins ON slideshows.author_id = admins.id")
                .mapToBean(Slideshow.class)
                .list());
    }

    public void setCurrentSlideshow(int slideId) {
        jdbi.useHandle(handle -> handle.createUpdate(
                "UPDATE `active-slideshow` SET id = :id")
                .bind("id", slideId)
                .execute());
    }

    public Integer getCurrentSlideshow() {
        return jdbi.withHandle(handle -> handle.createQuery(
                "SELECT id FROM `active-slideshow`")
        ).mapTo(Integer.class).findOnly();
    }
}

