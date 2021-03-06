package org.softwire.training.slideshowbob.models.database;

import org.jdbi.v3.core.mapper.Nested;

public class SlideshowSlide {

    private int id;
    private int slideshowId;
    private int slideId;
    private int order;

    private Slideshow slideshow;
    private Image slide;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Nested("sshow")
    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        this.slideshow = slideshow;
    }

    @Nested("image")
    public Image getSlide() {
        return slide;
    }

    public void setSlide(Image slide) {
        this.slide = slide;
    }
}
