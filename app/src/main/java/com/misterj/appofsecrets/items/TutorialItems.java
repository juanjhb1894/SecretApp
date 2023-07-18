package com.misterj.appofsecrets.items;

public class TutorialItems {

    private int id;
    private String name;
    private String url;
    private String coverPictureUrl;
    private String description;
    private Integer status;
    private String author;
    private String uploadedAt;
    private int target;

    public TutorialItems(int id, String name, String url, String coverPictureUrl, String description, Integer status, String author, String uploadedAt, int target) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.coverPictureUrl = coverPictureUrl;
        this.description = description;
        this.status = status;
        this.author = author;
        this.uploadedAt = uploadedAt;
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverPictureUrl() {
        return coverPictureUrl;
    }

    public void setCoverPictureUrl(String coverPictureUrl) {
        this.coverPictureUrl = coverPictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
