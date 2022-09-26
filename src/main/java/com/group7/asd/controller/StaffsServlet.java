package com.group7.asd.controller;

import com.alibaba.fastjson.JSON;
import com.group7.asd.dao.Database;
import com.group7.asd.model.Staff;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetStaffsServlet", value = "/StaffsServlet")
public class StaffsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
         Map<String, Object> result = new HashMap<String, Object>();
         String actionType = request.getParameter("actionType");
         switch (actionType) {
             case "add":
                 String staffFormAdd = request.getParameter("staffForm");
                 Staff staffAdd = JSON.parseObject(staffFormAdd, Staff.class);
                 Database.instance().addStaff(staffAdd);
                 result.put("info", "success");
                 request.setCharacterEncoding("UTF-8");
                 response.setContentType("text/html;charset=utf-8");
                 response.getWriter().println(new JSONObject(result).toString());
                 break;
             case "edit":
                 String staffFormEdit = request.getParameter("staffForm");
                 Staff staffEdit = JSON.parseObject(staffFormEdit, Staff.class);
                 Database.instance().editStaff(staffEdit);
                 result.put("info", "success");
                 request.setCharacterEncoding("UTF-8");
                 response.setContentType("text/html;charset=utf-8");
                 response.getWriter().println(new JSONObject(result).toString());
                 break;
             case "delete":
                 String staffForm = request.getParameter("staffForm");
                 Staff staff = JSON.parseObject(staffForm, Staff.class);
                 Database.instance().deleteStaff(staff.getId());
                 result.put("info", "success");
                 request.setCharacterEncoding("UTF-8");
                 response.setContentType("text/html;charset=utf-8");
                 response.getWriter().println(new JSONObject(result).toString());
                 break;
             case "query":
                 List<Staff> staffs = new ArrayList<Staff>();
                 try {
                     staffs = Database.instance().getStaffs();
                     result.put("tableData", staffs);
                     result.put("info", "success");
                 } catch (Exception e) {
                     result.put("info", "error");
                 }
                 request.setCharacterEncoding("UTF-8");
                 response.setContentType("text/html;charset=utf-8");
                 response.getWriter().println(new JSONObject(result).toString());
                 break;
         }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
