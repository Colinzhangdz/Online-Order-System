package com.group7.asd.dao;

import com.group7.asd.model.Delivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class DeliveryDao {
    public void createDelivery(Delivery delivery) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "asd";
            String password = "bzsg.+CiGd2f";
            String connectionUrl = "jdbc:mysql://asd-database.mysql.database.azure.com/demo";
            Connection conn = DriverManager.getConnection(connectionUrl, username, password);

            String sql = "INSERT INTO delivery (delivery_id, user_id, order_id, driver_name, driver_lat, driver_lng, restaurant_lat, restaurant_lng, customer_lat, customer_lng) VALUES ('"+delivery.getDelivery_id()+"', "+delivery.getUser_id()+", "+delivery.getOrder_id()+", '"+delivery.getDriver_name()+"', "+delivery.getDriver_lat()+", "+delivery.getDriver_lng()+", "+delivery.getRestaurant_lat()+", "+delivery.getRestaurant_lng()+", "+delivery.getCustomer_lat()+", "+delivery.getCustomer_lng()+");";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred at createEvaluation()");
        }
    }

    //get all delivery
    public ArrayList<Delivery> getAllDelivery() {
        ArrayList<Delivery> deliveryList = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "asd";
            String password = "bzsg.+CiGd2f";
            String connectionUrl = "jdbc:mysql://asd-database.mysql.database.azure.com/demo";
            Connection conn = DriverManager.getConnection(connectionUrl, username, password);

            String sql = "SELECT * FROM delivery";
            Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Delivery delivery = new Delivery();
                delivery.setDelivery_id(rs.getString("delivery_id"));
                delivery.setUser_id(rs.getInt("user_id"));
                delivery.setOrder_id(rs.getInt("order_id"));
                delivery.setDriver_name(rs.getString("driver_name"));
                delivery.setDriver_lat(rs.getDouble("driver_lat"));
                delivery.setDriver_lng(rs.getDouble("driver_lng"));
                delivery.setRestaurant_lat(rs.getDouble("restaurant_lat"));
                delivery.setRestaurant_lng(rs.getDouble("restaurant_lng"));
                delivery.setCustomer_lat(rs.getDouble("customer_lat"));
                delivery.setCustomer_lng(rs.getDouble("customer_lng"));
                deliveryList.add(delivery);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred at getAllDelivery()");
        }
        return deliveryList;
    }

    //update delivery by delivery_id
    public void updateDelivery(Delivery delivery) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "asd";
            String password = "bzsg.+CiGd2f";
            String connectionUrl = "jdbc:mysql://asd-database.mysql.database.azure.com/demo";
            Connection conn = DriverManager.getConnection(connectionUrl, username, password);

            String sql = "UPDATE delivery SET user_id = "+delivery.getUser_id()+", order_id = "+delivery.getOrder_id()+", driver_name = '"+delivery.getDriver_name()+"', driver_lat = "+delivery.getDriver_lat()+", driver_lng = "+delivery.getDriver_lng()+", restaurant_lat = "+delivery.getRestaurant_lat()+", restaurant_lng = "+delivery.getRestaurant_lng()+", customer_lat = "+delivery.getCustomer_lat()+", customer_lng = "+delivery.getCustomer_lng()+" WHERE delivery_id = '"+delivery.getDelivery_id()+"';";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred at updateDelivery()");
        }
    }
}
