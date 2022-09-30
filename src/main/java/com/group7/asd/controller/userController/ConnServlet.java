
package com.group7.asd.controller.userController;

import com.group7.asd.dao.DBConnector;
import com.group7.asd.dao.UserDBManager;
import com.group7.asd.dao.UserLogDBManager;

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

@WebServlet(name = "ConnServlet", urlPatterns = {"/ConnServlet"})
public class ConnServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private DBConnector db;
    
    private UserDBManager userDBManager;
    private UserLogDBManager userLogDBManager;

    private Connection conn;

    @Override //Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DBConnector();  //Create a database connection when the application starts
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        new Validator().clear(session);
        conn = db.openConnection();       // Create a DB connection
        try {
            userDBManager = new UserDBManager(conn);  //Create DB managers
            userLogDBManager = new UserLogDBManager(conn);

            
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //export the DB manager to the view-session (JSPs)
        session.setAttribute("userDBManager", userDBManager);
        session.setAttribute("userLogDBManager", userLogDBManager);

        
        response.sendRedirect("register.jsp");
    }
    
    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
        try {
            db.closeConnection();  //Close the DB connection once the session is terminated
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
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
