package com.group7.asd.controller;

import com.alibaba.fastjson.JSON;
import com.group7.asd.dao.Database;
import com.group7.asd.model.Product;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        String actionType = request.getParameter("actionType");
        switch (actionType) {
            case "add":
                String productFormAdd = request.getParameter("productForm");
                Product productAdd = JSON.parseObject(productFormAdd, Product.class);
                Database.instance().addProduct(productAdd);
                result.put("info", "success");
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(new JSONObject(result).toString());
                break;
            case "edit":
                String productFormEdit = request.getParameter("productForm");
                Product productEdit = JSON.parseObject(productFormEdit, Product.class);
                Database.instance().editProduct(productEdit);
                result.put("info", "success");
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(new JSONObject(result).toString());
                break;
            case "delete":
                String productForm = request.getParameter("productForm");
                Product product = JSON.parseObject(productForm, Product.class);
                Database.instance().deleteProduct(product.getId());
                result.put("info", "success");
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(new JSONObject(result).toString());
                break;
            case "query":
                List<Product> products = new ArrayList<Product>();
                try {
                    products = Database.instance().getProducts();
                    result.put("tableData", products);
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





