/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group7.asd.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "SearchUserLogServlet", urlPatterns = {"/SearchUserLogServlet"})
public class SearchUserLogServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Validator validator = new Validator();
            validator.clear(session);
           
            request.getRequestDispatcher("userslog.jsp").include(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddLoginLogServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("userslog.jsp");
        }

    }



}
