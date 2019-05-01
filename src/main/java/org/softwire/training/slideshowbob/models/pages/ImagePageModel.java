package org.softwire.training.slideshowbob.models.pages;

import org.softwire.training.slideshowbob.models.database.Image;

import java.util.List;

public class ImagePageModel {

    private List<Image> images;

    public ImagePageModel(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
