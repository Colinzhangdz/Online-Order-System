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

public class RegisterServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String usertype = request.getParameter("usertype");

        UserDBManager manager = (UserDBManager) session.getAttribute("userDBManager");
        validator.clear(session);

        if (!validator.validateEmail(email)) {
            session.setAttribute("error", "Error: Email format is incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validateName(fullname)) {
            session.setAttribute("error", "Error: Name format is incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("error", "Error: Password format is incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validatePassword(phone)) {
            session.setAttribute("error", "Error: Phone format is incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else {
            try {
                int exist = manager.checkEmailExist(email);
                if (exist !=0) {
                    session.setAttribute("error", "User already exists in the Database");
                    request.getRequestDispatcher("register.jsp").include(request, response);
                } else {
                    User tempUser= new User(0, email, password, fullname, phone, usertype, true);
                    manager.addUser(tempUser);
                    User user =  manager.findUser(email, password);;
                    session.setAttribute("user", user);
                    response.sendRedirect("AddLoginLogServlet");
                }
            } catch (SQLException ex) {
                session.setAttribute("error", ex.getMessage());
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
        }
    }

}
