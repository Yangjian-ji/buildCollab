package com.example.buildcollab.utils;

import java.util.List;

public class Users {

    private String userId;
    private String name;
    private String description;
    private String portfolio;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    List<String> areaOfInterest;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public List<String> getAreOfInterest() {
        return areaOfInterest;
    }

    public void setAreOfInterest(List<String> areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

}
