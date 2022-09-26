package com.group7.asd.controller;

import com.alibaba.fastjson.JSON;
import com.group7.asd.dao.Database;
import com.group7.asd.model.Customer;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustomerServlet", value = "/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        String actionType = request.getParameter("actionType");
        switch (actionType) {
            case "add":
                String customerFormAdd = request.getParameter("customerForm");
                Customer customerAdd = JSON.parseObject(customerFormAdd, Customer.class);
                Database.instance().addCustomer(customerAdd);
                result.put("info", "success");
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(new JSONObject(result).toString());
                break;
            case "edit":
                String customerFormEdit = request.getParameter("customerForm");
                Customer customerEdit = JSON.parseObject(customerFormEdit, Customer.class);
                Database.instance().editCustomer(customerEdit);
                result.put("info", "success");
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(new JSONObject(result).toString());
                break;
            case "delete":
                String customerForm = request.getParameter("customerForm");
                Customer customer = JSON.parseObject(customerForm, Customer.class);
                Database.instance().deleteCustomer(customer.getId());
                result.put("info", "success");
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(new JSONObject(result).toString());
                break;
            case "query":
                List<Customer> customers = new ArrayList<Customer>();
                try {
                    customers = Database.instance().getCustomers();
                    result.put("tableData", customers);
                    result.put("info", "success");
                } catch (Exception e) {
                    result.put("info", "error");
                }
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(new JSONObject(result).toString());
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
