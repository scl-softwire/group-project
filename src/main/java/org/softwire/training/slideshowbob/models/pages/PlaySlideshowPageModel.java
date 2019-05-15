package org.softwire.training.slideshowbob.models.pages;

import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.database.SlideshowSlide;

import java.util.List;

public class PlaySlideshowPageModel {

    private Slideshow slideshow;
    private List<SlideshowSlide> slideshowSlides;

    public PlaySlideshowPageModel(Slideshow slideshow, List<SlideshowSlide> slideshowSlides) {
        this.slideshow = slideshow;
        this.slideshowSlides = slideshowSlides;
    }

    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        this.slideshow = slideshow;
    }

    public List<SlideshowSlide> getSlideshowSlides() {
        return slideshowSlides;
    }

    public void setSlideshowSlides(List<SlideshowSlide> slideshowSlides) {
        this.slideshowSlides = slideshowSlides;
    }

}
