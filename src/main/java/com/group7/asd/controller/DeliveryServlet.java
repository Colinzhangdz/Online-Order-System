package com.group7.asd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.group7.asd.dao.DeliveryDao;
import com.group7.asd.model.Delivery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@WebServlet(name = "Delivery", value = "/delivery")
public class DeliveryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeliveryDao deliveryDao = new DeliveryDao();
        ArrayList<Delivery> deliveryArrayList = deliveryDao.getAllDelivery();
        JSONArray JSONObj = (JSONArray) JSON.toJSON(deliveryArrayList);
        String JsonStr = JSON.toJSONString(JSONObj, SerializerFeature.PrettyFormat);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JsonStr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Delivery delivery = new Delivery();
        delivery.setDelivery_id(UUID.randomUUID().toString().replace("-", ""));
        Random random = new Random();
        delivery.setUser_id(random.nextInt(100));
        delivery.setOrder_id(random.nextInt(100));
        String[] driverList = {"Liam", "Noah", "William", "James", "Logan", "Benjamin", "Mason", "Elijah", "Oliver", "Jacob"};
        delivery.setDriver_name(driverList[random.nextInt(10)]);
        double xMax = 151.230463;
        double xMin = 151.188921;
        double yMax = -33.876490;
        double yMin = -33.904216;
        delivery.setDriver_lat(((Math.random() * (yMax - yMin)) + yMin));
        delivery.setDriver_lng(((Math.random() * (xMax - xMin)) + xMin));
        delivery.setRestaurant_lat(((Math.random() * (yMax - yMin)) + yMin));
        delivery.setRestaurant_lng(((Math.random() * (xMax - xMin)) + xMin));
        delivery.setCustomer_lat(((Math.random() * (yMax - yMin)) + yMin));
        delivery.setCustomer_lng(((Math.random() * (xMax - xMin)) + xMin));
        DeliveryDao deliveryDao = new DeliveryDao();
        deliveryDao.createDelivery(delivery);
    }
}
