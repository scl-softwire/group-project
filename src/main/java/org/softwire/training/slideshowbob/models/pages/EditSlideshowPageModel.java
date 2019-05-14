package org.softwire.training.slideshowbob.models.pages;

import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.database.SlideshowSlide;

import java.util.List;

public class EditSlideshowPageModel {

    private Slideshow slideshow;
    private List<SlideshowSlide> slides;
    private List<Image> allImages;

    public EditSlideshowPageModel(Slideshow slideshow, List<SlideshowSlide> slides, List<Image> allImages) {
        this.slideshow = slideshow;
        this.slides = slides;
        this.allImages = allImages;
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

    public List<Image> getAllImages() {
        return allImages;
    }

    public void setAllImages(List<Image> allImages) {
        this.allImages = allImages;
    }
}
