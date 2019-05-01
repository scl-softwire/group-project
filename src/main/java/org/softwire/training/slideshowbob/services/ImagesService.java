package org.softwire.training.slideshowbob.services;

import org.softwire.training.slideshowbob.models.database.UploadImages;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ImagesService extends DatabaseService {

        public void uploadImage (UploadImages image) {
            jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO upload_images (date_time_stamp, image_name, author, license, url) " +
                    "VALUES (" + LocalDateTime.now() + ")"));
        }


}
