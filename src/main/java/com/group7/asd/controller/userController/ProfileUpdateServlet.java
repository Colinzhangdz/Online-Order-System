/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group7.asd.controller.userController;



import com.group7.asd.dao.UserDBManager;
import com.group7.asd.model.User;

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

/**
 *
 * @author Saqib
 */
@WebServlet(name = "ProfileUpdateServlet", urlPatterns = {"/ProfileUpdateServlet"})
public class ProfileUpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
