package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.SupBrand;
import com.itheima.service.BrandService;
import com.itheima.service.SupBrandService;
import com.itheima.service.impl.BrandServiceImpl;
import com.itheima.service.impl.SupBrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/supaddServlet")
public class SupAddServlet extends HttpServlet {

    private SupBrandService supbrandService = new SupBrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. Receive brand data
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        System.out.println(params);

        //Convert to Brand object
        SupBrand brand = JSON.parseObject(params, SupBrand.class);

        //2. Call service to add
        supbrandService.add(brand);

        //3.  Response success marker
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}