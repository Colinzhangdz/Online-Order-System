package com.group7.asd.controller.userController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group7.asd.dao.DBConnector;
import com.group7.asd.dao.UserDBManager;
import com.group7.asd.model.User;
import com.group7.asd.utils.ResponseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OrderPaymentServlet", urlPatterns = {"/saveOrder"})
public class OrderPaymentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

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
		if("save".equals(action)){
			try {
				this.saveOrder(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		conn = db.openConnection();
		UserDBManager manager = new UserDBManager(conn);  //Create DB managers
		String orderDetail = request.getParameter("orderDetail");
		String totalMoney = request.getParameter("totalMoney");
		JSONArray jsonArray= JSONArray.parseArray(orderDetail);
		String orderNo = UUID.randomUUID().toString();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject object = (JSONObject) jsonArray.get(i);
			manager.saveOrderDetail(object,orderNo);
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = "";
		if(user != null) {
			userId = user.getUserId() + "";
		}
		Double money = Double.parseDouble(totalMoney);
		manager.saveOrder(orderNo,money,userId);
		boolean delFlag = true;
		try {
			ResponseUtil.write(delFlag, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
