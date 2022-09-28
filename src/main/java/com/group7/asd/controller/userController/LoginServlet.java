package com.group7.asd.controller.userController;

import com.group7.asd.model.User;
import com.group7.asd.dao.UserDBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

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



}