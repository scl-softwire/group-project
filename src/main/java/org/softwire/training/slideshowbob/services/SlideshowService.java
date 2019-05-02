package org.softwire.training.slideshowbob.services;

import org.jdbi.v3.core.statement.PreparedBatch;
import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.springframework.stereotype.Service;

@Service
public class SlideshowService extends DatabaseService {

    public void createSlideshow(Slideshow slideshow) {
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
            for (Image image : slideshow.getSlideList()) {
                batch.bind("slide_id", image.getId());
            }
            batch.execute();
        });
    }

//    // TODO return working slideshow - MAP TO Slideshow
//    public Slideshow getSlideshow(int id) {
//        return jdbi.useHandle(handle -> handle.createUpdate("SELECT * FROM `slideshows` WHERE id = :id")
//                .bind("id", id)
//                .execute());
//    }
//
//    // TODO return all images for a slideshow mapped to a SlideshowSlide class
//    public List<SlideshowSlide> getImagesforSlideshow(Slideshow slideshow){
//        return jdbi.useHandle(handle -> handle.createUpdate("SELECT * FROM `slideshow_slides` WHERE slideshow_id = :id")
//                .bind("id", slideshow.getId())
//                .execute());
//    }

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

