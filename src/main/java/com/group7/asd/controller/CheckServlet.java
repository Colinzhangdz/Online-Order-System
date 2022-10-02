package com.group7.asd.controller;

import com.alibaba.fastjson.JSON;
import com.group7.asd.dao.Database;
import com.group7.asd.model.Check;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CheckServlet", value = "/CheckServlet")
public class CheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
         Map<String, Object> result = new HashMap<String, Object>();
         String actionType = request.getParameter("actionType");
         if(actionType!=null&&actionType.equals("query")){
             List<Check> checks = new ArrayList<Check>();
             try {
                 checks = Database.instance().getChecks();
                 result.put("tableData", checks);
                 result.put("info", "success");
             } catch (Exception e) {
                 result.put("info", "error");
             }
             request.setCharacterEncoding("UTF-8");
             response.setContentType("text/html;charset=utf-8");
             response.getWriter().println(new JSONObject(result).toString());
         }else {
             BufferedReader reader = request.getReader();
             reader.readLine();
             reader.readLine();
             reader.readLine();
             String checkListJson =  reader.readLine();
             List<Check> checkList = JSON.parseArray(checkListJson, Check.class);
             for(Check check:checkList){
                 if(check.getId()!=null&&!check.equals("")){
                     Database.instance().editCheck(check);
                 }else{
                     Database.instance().addCheck(check);
                 }
             }
             result.put("info", "success");
             request.setCharacterEncoding("UTF-8");
             response.setContentType("text/html;charset=utf-8");
             response.getWriter().println(new JSONObject(result).toString());
         }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
