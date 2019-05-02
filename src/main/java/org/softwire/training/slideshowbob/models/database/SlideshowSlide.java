package org.softwire.training.slideshowbob.models.database;

public class SlideshowSlide {

    private Slideshow slideshowId;
    private Image slideId;
    private int order;

    public Slideshow getSlideshowId() {
        return slideshowId;
    }

    public void setSlideshowId(Slideshow slideshowId) {
        this.slideshowId = slideshowId;
    }

    public Image getSlideId() {
        return slideId;
    }

    public void setSlideId(Image slideId) {
        this.slideId = slideId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
