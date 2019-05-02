package org.softwire.training.slideshowbob.services;

import org.jdbi.v3.core.statement.PreparedBatch;
import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.database.SlideshowSlide;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideshowService extends DatabaseService {

    public void createSlideshow(Slideshow slideshow, List<Image> images) {
        // Create Slideshow in Slideshow Table

        jdbi.useHandle(handle -> {
            int id = handle.createUpdate("INSERT INTO slideshows " +
                    "(author_username) VALUES (:author_username)")
                    .bind("author_username", slideshow.getAdminUser())
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .findOnly();

            PreparedBatch batch = handle.prepareBatch("INSERT INTO slideshow-slides " +
                    "(slideshow_id, slide_id) VALUES (:slideshow_id, :slide_id)")
                    .bind("slideshow_id", id);
            for (Image image : images) {
                batch.bind("slide_id", image.getId());
            }
            batch.execute();
        });
    }

    public List<SlideshowSlide> getImagesforSlideshow(int id){
        return jdbi.withHandle(handle -> handle.createQuery(
                "SELECT slideshow_slides.slideshow_id, slideshow_slides.slide_id, slideshow_slides.order, " +
                        "slideshows.id AS slideshow_id, slideshows.author_id AS slideshow_id, " +
                        "upload_images.id AS image_id, " +
                            "upload_images.date_time_stamp AS image_date_time_stamp, " +
                            "upload_images.image_name AS image_image_name, " +
                            "upload_images.author AS image_author, " +
                            "upload_images.license AS image_license, " +
                            "upload_images.url AS image_url " +
                        "FROM slideshow_slides " +
                            "INNER JOIN slideshows ON slideshow_slides.slideshow_id = slideshows.id " +
                            "INNER JOIN upload_images ON slideshow_slides.id = upload_images.id " +
                        "WHERE slideshow_id = :id")
                .bind("id", id)
                .mapToBean(SlideshowSlide.class)
                .list());
    }

    public void deleteSlideshow(int id) {
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM slideshow-slides " +
                "WHERE slideshow_id = :slideshow_id")
                .bind("slideshow_id", id)
                .execute());
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM slideshows " +
                "WHERE id = :id").bind("id", id)
                .execute());
    }
}

