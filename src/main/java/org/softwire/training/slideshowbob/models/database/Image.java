package org.softwire.training.slideshowbob.models.database;

import org.jdbi.v3.core.mapper.Nested;

import java.time.LocalDateTime;

public class Image {
    private int id;
    LocalDateTime dateTimeStamp;
    String imageName;
    AdminUser author;
    String license;
    String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(LocalDateTime dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Nested("adminUser")
    public AdminUser getAuthor() {
        return author;
    }

    public void setAuthor(AdminUser author) {
        this.author = author;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
