package com.example.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveInfo {

    @SerializedName("id")
    @Expose
    public Integer id;
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
    public Integer temperature;
    @SerializedName("humidity")
    @Expose
    public Integer humidity;
    @SerializedName("rate_of_occurrence")
    @Expose
    public Integer rateOfOccurrence;
    @SerializedName("date")
    @Expose
    public String date;

    public Integer getId() {
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

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getRateOfOccurrence() {
        return rateOfOccurrence;
    }

    public String getDate() {
        return date;
    }
}
