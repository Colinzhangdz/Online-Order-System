/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group7.asd.controller;

import com.group7.asd.dao.UserDBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProfileDeleteServlet", urlPatterns = {"/ProfileDeleteServlet"})
public class ProfileDeleteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int userId = Integer.parseInt(request.getParameter("userid"));
            HttpSession session = request.getSession();
            Validator validator = new Validator();
            
            UserDBManager manager = (UserDBManager) session.getAttribute("userDBManager");
            validator.clear(session);
            manager.deleteUser(userId);
            response.sendRedirect("LogoutServlet");
        } catch (Exception ex) {
            response.sendRedirect("profile.jsp");
        }
    }


}
