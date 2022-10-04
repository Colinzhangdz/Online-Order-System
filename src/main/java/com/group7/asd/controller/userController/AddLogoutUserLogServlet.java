/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group7.asd.controller.userController;

import com.group7.asd.dao.DBConnector;
import com.group7.asd.dao.UserDBManager;
import com.group7.asd.dao.UserLogDBManager;
import com.group7.asd.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saqib
 */
@WebServlet(name = "AddLogoutUserLogServlet", urlPatterns = {"/AddLogoutUserLogServlet"})
public class AddLogoutUserLogServlet extends HttpServlet {

    private DBConnector db;

    private Connection conn;


    @Override //Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DBConnector();  //Create a database connection when the application starts
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
            throws ServletException, IOException, SQLException {
        try {
            HttpSession session = request.getSession();
            Validator validator = new Validator();
            User user = (User) session.getAttribute("user");

            conn = db.openConnection();
            UserLogDBManager manager = new UserLogDBManager(conn);

            validator.clear(session);

            manager.addUserLogoutLog(user.getUserId());
            response.sendRedirect("LogoutServlet");
        } catch (SQLException ex) {
            Logger.getLogger(AddLoginLogServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("LogoutServlet");
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
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
