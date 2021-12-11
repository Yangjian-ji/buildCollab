package com.example.buildcollab.utils;

import java.util.List;

public class Groups {

    private String groupId;
    private String ownerId;
    private String title;
    private String description;
    List<String> areaOfInterest;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAreOfInterest() {
        return areaOfInterest;
    }

    public void setAreOfInterest(List<String> areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

}
