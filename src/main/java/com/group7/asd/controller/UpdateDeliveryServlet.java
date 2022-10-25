package com.group7.asd.controller;

import com.group7.asd.dao.DeliveryDao;
import com.group7.asd.model.Delivery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateDelivery", value = "/updateDelivery")
public class UpdateDeliveryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Delivery delivery = new Delivery();
        delivery.setDelivery_id(request.getParameter("delivery_id"));
        delivery.setUser_id(Integer.parseInt(request.getParameter("user_id")));
        delivery.setOrder_id(Integer.parseInt(request.getParameter("order_id")));
        delivery.setDriver_name(request.getParameter("driver_name"));
        delivery.setDriver_lat(Double.parseDouble(request.getParameter("driver_lat")));
        delivery.setDriver_lng(Double.parseDouble(request.getParameter("driver_lng")));
        delivery.setRestaurant_lat(Double.parseDouble(request.getParameter("restaurant_lat")));
        delivery.setRestaurant_lng(Double.parseDouble(request.getParameter("restaurant_lng")));
        delivery.setCustomer_lat(Double.parseDouble(request.getParameter("customer_lat")));
        delivery.setCustomer_lng(Double.parseDouble(request.getParameter("customer_lng")));
        DeliveryDao deliveryDao = new DeliveryDao();
        deliveryDao.updateDelivery(delivery);
    }
}
