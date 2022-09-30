package com.group7.asd.model;

import java.util.ArrayList;
import java.util.List;

public class Evaluation {
    private String id;
    private int platformRating;
    private String feedback;
    private String deliveryPersonFeedback;
    private List<String> attraction = new ArrayList<>();
    private int foodOverallRating;
    private int foodPackingRating;
    private int foodTasteRating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPlatformRating() {
        return platformRating;
    }

    public void setPlatformRating(int platformRating) {
        this.platformRating = platformRating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDeliveryPersonFeedback() {
        return deliveryPersonFeedback;
    }

    public void setDeliveryPersonFeedback(String deliveryPersonFeedback) {
        this.deliveryPersonFeedback = deliveryPersonFeedback;
    }

    public List<String> getAttraction() {
        return attraction;
    }

    public void setAttraction(List<String> attraction) {
        this.attraction = attraction;
    }

    public int getFoodOverallRating() {
        return foodOverallRating;
    }

    public void setFoodOverallRating(int foodOverallRating) {
        this.foodOverallRating = foodOverallRating;
    }

    public int getFoodPackingRating() {
        return foodPackingRating;
    }

    public void setFoodPackingRating(int foodPackingRating) {
        this.foodPackingRating = foodPackingRating;
    }

    public int getFoodTasteRating() {
        return foodTasteRating;
    }

    public void setFoodTasteRating(int foodTasteRating) {
        this.foodTasteRating = foodTasteRating;
    }
}
