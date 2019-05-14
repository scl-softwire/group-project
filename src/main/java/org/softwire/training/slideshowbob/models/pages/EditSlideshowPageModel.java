package org.softwire.training.slideshowbob.models.pages;

import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.database.SlideshowSlide;

import java.util.List;

public class EditSlideshowPageModel {

    Slideshow slideshow;
    List<SlideshowSlide> slides;

    public EditSlideshowPageModel(Slideshow slideshow, List<SlideshowSlide> slides) {
        this.slideshow = slideshow;
        this.slides = slides;
    }

    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        this.slideshow = slideshow;
    }

    public List<SlideshowSlide> getSlides() {
        return slides;
    }

    public void setSlides(List<SlideshowSlide> slides) {
        this.slides = slides;
    }
}
