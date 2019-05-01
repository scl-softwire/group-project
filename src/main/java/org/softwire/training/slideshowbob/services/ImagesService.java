package org.softwire.training.slideshowbob.services;

import org.softwire.training.slideshowbob.models.database.UploadImages;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImagesService extends DatabaseService {

        public void uploadImage (UploadImages image) {
            jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO upload_images (date_time_stamp, image_name, author, license, url) " +
                    "VALUES (" + LocalDateTime.now() + ")"));
        }

        public List<UploadImages> getAllImages () {
            return jdbi.withHandle(handle ->
                    handle.createQuery("SELECT * FROM upload_images ")
                    .mapToBean(UploadImages.class)
                    .list()
                    );
        }


}
