/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group7.asd.controller;

import com.group7.asd.model.User;
import com.group7.asd.dao.UserDBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProfileUpdateServlet", urlPatterns = {"/ProfileUpdateServlet"})
public class ProfileUpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        
        UserDBManager manager = (UserDBManager) session.getAttribute("userDBManager");
        validator.clear(session);
        
        if (!validator.validateEmail(email)) {
            session.setAttribute("error", "Error: Email format is incorrect");
            request.getRequestDispatcher("profile.jsp").include(request, response);
        } else if (!validator.validateName(fullname)) {
            session.setAttribute("error", "Error: Name format is incorrect");
            request.getRequestDispatcher("profile.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("error", "Error: Password format is incorrect");
            request.getRequestDispatcher("profile.jsp").include(request, response);
        } else if (!validator.validatePassword(phone)) {
            session.setAttribute("error", "Error: Phone format is incorrect");
            request.getRequestDispatcher("profile.jsp").include(request, response);
        } else {
            try {
                
                User tempUser = new User(0, email, password, fullname, phone, "CUSTOMER", true);
                
                manager.updateUser(tempUser);
                session.setAttribute("user", manager.findUser(email, password));
                 session.setAttribute("success", "Profile updated ");
                request.getRequestDispatcher("profile.jsp").include(request, response);
               
                
            } catch (SQLException ex) {
                session.setAttribute("error", ex.getMessage());
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("profile.jsp").include(request, response);
            }
        }
    }


}
