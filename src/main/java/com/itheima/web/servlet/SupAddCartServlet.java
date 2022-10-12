package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.group7.asd.model.User;
import com.itheima.pojo.Cart;
import com.itheima.service.SupCartService;
import com.itheima.service.impl.SupCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/supaddCartServlet")
public class SupAddCartServlet extends HttpServlet {

    private SupCartService supcartService = new SupCartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. Receive brand data
        BufferedReader br = request.getReader();
        String params = br.readLine();//json
        System.out.println(params);
        //if no user login it will cant add
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null){
            response.getWriter().write("fail");
        }
        //转为Cart对象
        Cart brand = JSON.parseObject(params, Cart.class);
        brand.setUid(user.getUserId());
        //2. Call service add
        supcartService.add(brand);

        //3.  Response success marker
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}