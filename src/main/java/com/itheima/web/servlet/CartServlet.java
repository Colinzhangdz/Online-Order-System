package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.group7.asd.model.User;
import com.itheima.pojo.Cart;
import com.itheima.service.CartService;
import com.itheima.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {

    private CartService cs = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId=request.getParameter("uid");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
/*if(user == null){
    response.getWriter().write(JSON.toJSONString(new ArrayList()));
}
        Integer uid = user.getUserId();*/
        //1. 调用service查询
        List<Cart> carts = cs.selectAll(Integer.valueOf(userId));


        //2. 转为JSON
        String jsonString = JSON.toJSONString(carts);


        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
