
package com.group7.asd.controller;



import com.group7.asd.model.User;
import com.group7.asd.dao.UserLogDBManager;

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


@WebServlet(name = "AddLoginLogServlet", urlPatterns = {"/AddLoginLogServlet"})
public class AddLoginLogServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Validator validator = new Validator();
            User user = (User) session.getAttribute("user");

            UserLogDBManager manager = (UserLogDBManager) session.getAttribute("userLogDBManager");
            validator.clear(session);

            manager.createUserLog(user.getUserId());
            response.sendRedirect("ProfileRedirectServlet");

        } catch (SQLException ex) {
            Logger.getLogger(AddLoginLogServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("LoginRedirectServlet");
        }

    }


}
