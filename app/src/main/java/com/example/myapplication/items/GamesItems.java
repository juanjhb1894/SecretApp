package com.example.myapplication.items;

public class GamesItems {
    private int id;
    private String iamge_url;
    private String name;
    private String description;
    private String rules;

    public GamesItems(int id, String iamge_url, String name, String description, String rules) {
        this.id = id;
        this.iamge_url = iamge_url;
        this.name = name;
        this.description = description;
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIamge_url() {
        return iamge_url;
    }

    public void setIamge_url(String iamge_url) {
        this.iamge_url = iamge_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String description) {
        this.rules = description;
    }
}
