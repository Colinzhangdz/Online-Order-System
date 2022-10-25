package com.group7.asd.model;

public class Delivery {
    private String delivery_id;
    private int user_id;
    private int order_id;
    private String driver_name;
    private double driver_lat;
    private double driver_lng;
    private double restaurant_lat;
    private double restaurant_lng;
    private double customer_lat;
    private double customer_lng;

    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public double getDriver_lat() {
        return driver_lat;
    }

    public void setDriver_lat(double driver_lat) {
        this.driver_lat = driver_lat;
    }

    public double getDriver_lng() {
        return driver_lng;
    }

    public void setDriver_lng(double driver_lng) {
        this.driver_lng = driver_lng;
    }

    public double getRestaurant_lat() {
        return restaurant_lat;
    }

    public void setRestaurant_lat(double restaurant_lat) {
        this.restaurant_lat = restaurant_lat;
    }

    public double getRestaurant_lng() {
        return restaurant_lng;
    }

    public void setRestaurant_lng(double restaurant_lng) {
        this.restaurant_lng = restaurant_lng;
    }

    public double getCustomer_lat() {
        return customer_lat;
    }

    public void setCustomer_lat(double customer_lat) {
        this.customer_lat = customer_lat;
    }

    public double getCustomer_lng() {
        return customer_lng;
    }

    public void setCustomer_lng(double customer_lng) {
        this.customer_lng = customer_lng;
    }
}
