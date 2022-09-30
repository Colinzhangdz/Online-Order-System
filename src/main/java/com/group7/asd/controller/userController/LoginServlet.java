
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

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        String password = request.getParameter("password");

        UserDBManager manager = (UserDBManager) session.getAttribute("userDBManager");
        validator.clear(session);

        if (!validator.validateEmail(email)) {
            session.setAttribute("error", "Error: Email format is incorrect");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("error", "Error: Password format is incorrect");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else {
            try {
                int existStatus = manager.validateUser(email, password);
                switch (existStatus) {
                    case 0:
                        session.setAttribute("error", "Invalid User Name Or Password ");
                        request.getRequestDispatcher("login.jsp").include(request, response);
                        break;
                    case 2:
                        session.setAttribute("error", "Your account is not activate");
                        request.getRequestDispatcher("login.jsp").include(request, response);
                        break;
                    default:
                        User user = manager.findUser(email, password);
                        session.setAttribute("user", user);
                       response.sendRedirect("AddLoginLogServlet");
                        break;
                }
            } catch (SQLException ex) {
                session.setAttribute("error", ex.getMessage());
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("login.jsp").include(request, response);
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




