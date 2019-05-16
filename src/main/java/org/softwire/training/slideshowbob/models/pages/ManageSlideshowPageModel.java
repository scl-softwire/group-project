package org.softwire.training.slideshowbob.models.pages;

import org.softwire.training.slideshowbob.models.database.Slideshow;

import java.util.List;

public class ManageSlideshowPageModel {

    private List<Slideshow> slideshows;
    private Slideshow activeSlideshow;
    private boolean failed;

    public ManageSlideshowPageModel(List<Slideshow> slideshows, Slideshow activeSlideshow, boolean failed) {

        this.slideshows = slideshows;
        this.activeSlideshow = activeSlideshow;
        this.failed = failed;
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

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }
}
