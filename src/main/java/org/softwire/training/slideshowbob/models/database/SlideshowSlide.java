package org.softwire.training.slideshowbob.models.database;

import org.jdbi.v3.core.mapper.Nested;

public class SlideshowSlide {

    private int slideshowId;
    private int slideId;
    private int order;

    private Slideshow slideshow;
    private Image slide;

    public int getSlideshowId() {
        return slideshowId;
    }

    public void setSlideshowId(int slideshowId) {
        this.slideshowId = slideshowId;
    }

    public int getSlideId() {
        return slideId;
    }

    public void setSlideId(int slideId) {
        this.slideId = slideId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Nested("slideshow")
    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        this.slideshow = slideshow;
    }

    @Nested("slide")
    public Image getSlide() {
        return slide;
    }

    public void setSlide(Image slide) {
        this.slide = slide;
    }
}
