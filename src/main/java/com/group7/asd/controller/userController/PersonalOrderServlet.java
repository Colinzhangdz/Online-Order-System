package com.group7.asd.controller.userController;

import com.alibaba.fastjson.JSONArray;
import com.group7.asd.dao.DBConnector;
import com.group7.asd.dao.Database;
import com.group7.asd.dao.UserDBManager;
import com.group7.asd.model.OrderDetail;
import com.group7.asd.model.OrderInformation;
import com.group7.asd.model.User;
import com.group7.asd.utils.ResponseUtil;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "PersonalOrderServlet", urlPatterns = {"/personalOrder"})
public class PersonalOrderServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    private DBConnector db;

    private Connection conn;

    @Override
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if("selectPersonalOrder".equals(action)){
            try {
                this.selectPersonalOrder(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void selectPersonalOrder(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException, SQLException {
        conn = db.openConnection();
        UserDBManager manager = new UserDBManager(conn);
        String orderNo = request.getParameter("orderNo");
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String userId = "";
        if(user != null) {
            userId = user.getUserId() + "";
        }
        if(userId.equals("")) {
            result.put("info", "error");
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(new JSONObject(result).toString());
        }
        List<OrderInformation> orderInformationList =  Database.instance().getOrderList(userId);
        List<OrderDetail> orderDetailList = Database.instance().getOrderDetailList();
        List<OrderInformation> newOrderInformationList = new ArrayList<>();
        for(int i = 0;i<orderInformationList.size();i++) {
            int j = i;
            List<OrderDetail> itemOrderDetailList = orderDetailList.stream().filter(item -> orderInformationList.get(j).getOrderNo().equals(item.getOrder_no())).collect(Collectors.toList());
            orderInformationList.get(j).setOrderDetailList(itemOrderDetailList);
            if(("").equals(orderNo) || (!("").equals(orderNo) && orderInformationList.get(j).getOrderNo().indexOf(orderNo) > -1)) {
                newOrderInformationList.add(orderInformationList.get(j));
            }
        }
        result.put("tableData", newOrderInformationList);
        result.put("info", "success");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(new JSONObject(result).toString());
    }
}
