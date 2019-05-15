package org.softwire.training.slideshowbob.models.pages;

import org.softwire.training.slideshowbob.models.database.Slideshow;

import java.util.List;

public class SelectActiveSlideshowPageModel {

    private List<Slideshow> slideshows;
    private Slideshow activeSlideshow;

    public SelectActiveSlideshowPageModel(List<Slideshow> slideshows, Slideshow activeSlideshow) {
        this.slideshows = slideshows;
        this.activeSlideshow = activeSlideshow;
    }

    public List<Slideshow> getSlideshows() {
        return slideshows;
    }

    public void setSlideshows(List<Slideshow> slideshows) {
        this.slideshows = slideshows;
    }

    public Slideshow getActiveSlideshow() {
        return activeSlideshow;
    }

    public void setActiveSlideshow(Slideshow activeSlideshow) {
        this.activeSlideshow = activeSlideshow;
    }
}
