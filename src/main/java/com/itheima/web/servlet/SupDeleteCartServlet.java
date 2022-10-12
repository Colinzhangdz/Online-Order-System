package com.itheima.web.servlet;

import com.itheima.service.SupCartService;
import com.itheima.service.impl.SupCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supdeleteCartServlet")
public class SupDeleteCartServlet extends HttpServlet {

    private SupCartService supcartService = new SupCartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        //Integer id = Integer.valueOf(request.getParameter("id"));
        //System.out.println(id);
//        BufferedReader br = request.getReader();
//        String params = br.readLine();//json
//        System.out.println(params);

        //Chanage Cart Obj

        //2. call service add
        supcartService.delete(id);
        //cartService.delete(id);

        //3. Response success ID
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
