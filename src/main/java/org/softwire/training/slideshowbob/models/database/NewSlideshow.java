package org.softwire.training.slideshowbob.models.database;

import java.util.List;

public class NewSlideshow {

    private Slideshow slideshow;
    private List<Integer> slideIds;

    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        this.slideshow = slideshow;
    }

    public List<Integer> getSlideIds() {
        return slideIds;
    }

    public void setSlideIds(List<Integer> slideIds) {
        this.slideIds = slideIds;
    }
}
