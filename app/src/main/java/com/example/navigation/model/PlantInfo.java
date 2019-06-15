package com.example.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantInfo {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("label")
    @Expose
    public String label;
    @SerializedName("stage_of_infection")
    @Expose
    public String stageOfInfection;
    @SerializedName("part_of_the_plant")
    @Expose
    public String partOfThePlant;
    @SerializedName("if_leaf")
    @Expose
    public String ifLeaf;
    @SerializedName("farm")
    @Expose
    public String farm;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("image")
    @Expose
    public Object image;
    @SerializedName("comments")
    @Expose
    public String comments;
    @SerializedName("temperature")
    @Expose
    public String temperature;
    @SerializedName("humidity")
    @Expose
    public String humidity;
    @SerializedName("rate_of_occurrence")
    @Expose
    public Integer rateOfOccurrence;
    @SerializedName("date")
    @Expose
    public String date;

    public PlantInfo(String id, String label, String stageOfInfection, String partOfThePlant, String ifLeaf, String farm, String location, Object image, String comments, String temperature, String humidity, Integer rateOfOccurrence, String date) {
        this.id = id;
        this.label = label;
        this.stageOfInfection = stageOfInfection;
        this.partOfThePlant = partOfThePlant;
        this.ifLeaf = ifLeaf;
        this.farm = farm;
        this.location = location;
        this.image = image;
        this.comments = comments;
        this.temperature = temperature;
        this.humidity = humidity;
        this.rateOfOccurrence = rateOfOccurrence;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getStageOfInfection() {
        return stageOfInfection;
    }

    public String getPartOfThePlant() {
        return partOfThePlant;
    }

    public String getIfLeaf() {
        return ifLeaf;
    }

    public String getFarm() {
        return farm;
    }

    public String getLocation() {
        return location;
    }

    public Object getImage() {
        return image;
    }

    public String getComments() {
        return comments;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public Integer getRateOfOccurrence() {
        return rateOfOccurrence;
    }

    public String getDate() {
        return date;
    }
}
