package com.tastey.baking.bakingapp.model;

/**
 * Created by lenovo on 3/7/2018.
 */

public class StepModel {
    int id;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;

    public StepModel(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
