package com.group7.asd.dao;

import com.group7.asd.model.Evaluation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EvaluationDao {
    public void createEvaluation(Evaluation evaluation) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "asd";
            String password = "bzsg.+CiGd2f";
            String connectionUrl = "jdbc:mysql://asd-database.mysql.database.azure.com/demo";
            Connection conn = DriverManager.getConnection(connectionUrl, username, password);

            String sql = "INSERT INTO evaluation (id, platform_rating, feedback, delivery_person_feedback, attraction, food_overall_rating, food_packing_rating, food_taste_rating) VALUES ('"+evaluation.getId()+"', "+evaluation.getPlatformRating()+", '"+evaluation.getFeedback()+"', '"+evaluation.getDeliveryPersonFeedback()+"', '"+evaluation.getAttraction().toString()+"', "+evaluation.getFoodOverallRating()+", "+evaluation.getFoodPackingRating()+", "+evaluation.getFoodTasteRating()+");";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred at createEvaluation()");
        }
    }

    public ArrayList<Evaluation> getAllEvaluations(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "asd";
            String password = "bzsg.+CiGd2f";
            String connectionUrl = "jdbc:mysql://asd-database.mysql.database.azure.com/demo";
            Connection conn = DriverManager.getConnection(connectionUrl, username, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM evaluation;");
            ArrayList<Evaluation> array = new ArrayList<Evaluation>();

            while (rs.next()) {
                Evaluation evaluation = new Evaluation();
                evaluation.setId(rs.getString("id"));
                evaluation.setPlatformRating(rs.getInt("platform_rating"));
                evaluation.setFeedback(rs.getString("feedback"));
                evaluation.setDeliveryPersonFeedback(rs.getString("delivery_person_feedback"));
                evaluation.setAttraction(Arrays.asList(rs.getString("attraction").substring(1, rs.getString("attraction").length() - 1).split(",")));
                evaluation.setFoodOverallRating(rs.getInt("food_overall_rating"));
                evaluation.setFoodPackingRating(rs.getInt("food_packing_rating"));
                evaluation.setFoodTasteRating(rs.getInt("food_taste_rating"));
                array.add(evaluation);
            }
            stmt.close();
            rs.close();
            conn.close();
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred at getAllEvaluations()");
            return new ArrayList<Evaluation>();
        }
    }
}
