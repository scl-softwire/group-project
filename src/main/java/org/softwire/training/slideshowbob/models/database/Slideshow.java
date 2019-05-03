package org.softwire.training.slideshowbob.models.database;

import org.jdbi.v3.core.mapper.Nested;

public class Slideshow {

    private int id;
    private AdminUser adminUser;
    private String slideshowName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlideshowName() {
        return slideshowName;
    }

    public void setSlideshowName(String slideshowName) {
        this.slideshowName = slideshowName;
    }

    @Nested("adminUser")
    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }
}
