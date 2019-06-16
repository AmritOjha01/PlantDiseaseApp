package com.example.navigation.model;

public class SaveResponse {

    private String label, stage_of_infection, part_of_the_plant, if_leaf, farm, location, image, comments, temperature, humidity, rate_of_occurrence, date;

    public SaveResponse(String label, String stage_of_infection, String part_of_the_plant, String if_leaf, String farm, String location, String image, String comments, String temperature, String humidity, String rate_of_occurrence, String date) {
        this.label = label;
        this.stage_of_infection = stage_of_infection;
        this.part_of_the_plant = part_of_the_plant;
        this.if_leaf = if_leaf;
        this.farm = farm;
        this.location = location;
        this.image = image;
        this.comments = comments;
        this.temperature = temperature;
        this.humidity = humidity;
        this.rate_of_occurrence = rate_of_occurrence;
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public String getStage_of_infection() {
        return stage_of_infection;
    }

    public String getPart_of_the_plant() {
        return part_of_the_plant;
    }

    public String getIf_leaf() {
        return if_leaf;
    }

    public String getFarm() {
        return farm;
    }

    public String getLocation() {
        return location;
    }

    public String getImage() {
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

    public String getRate_of_occurrence() {
        return rate_of_occurrence;
    }

    public String getDate() {
        return date;
    }
}
