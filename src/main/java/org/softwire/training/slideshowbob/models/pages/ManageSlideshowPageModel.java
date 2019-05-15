package org.softwire.training.slideshowbob.models.pages;

import org.softwire.training.slideshowbob.models.database.Slideshow;

import java.util.List;

public class ManageSlideshowPageModel {

    private List<Slideshow> slideshows;

    public ManageSlideshowPageModel(List<Slideshow> slideshows) {
        this.slideshows = slideshows;
    }

    public List<Slideshow> getSlideshows() {
        return slideshows;
    }

    public void setSlideshows(List<Slideshow> slideshows) {
        this.slideshows = slideshows;
    }
}
