package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.group7.asd.model.User;
import com.itheima.pojo.Cart;
import com.itheima.pojo.SupCart;
import com.itheima.service.CartService;
import com.itheima.service.SupCartService;
import com.itheima.service.impl.CartServiceImpl;
import com.itheima.service.impl.SupCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/supcartServlet")
public class SupCartServlet extends HttpServlet {

    private SupCartService scs = new SupCartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId=request.getParameter("uid");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
if(user == null){
    response.getWriter().write(JSON.toJSONString(new ArrayList()));
}
        Integer uid = user.getUserId();
        //1. Calling service queries
        List<SupCart> carts = scs.selectAll(Integer.valueOf(uid));


        //2. Chanage JSON
        String jsonString = JSON.toJSONString(carts);


        //3. Write Data
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
