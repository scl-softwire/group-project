package org.softwire.training.groupproject.models.page;

import org.softwire.training.groupproject.models.database.Technology;

import java.util.List;

public class AboutPageModel {
    private List<Technology> technologies;

    public List<Technology> getTechnologies() {
        return technologies;
    }
    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}
