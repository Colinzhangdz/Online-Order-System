import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group7.asd.dao.DBConnector;
import com.group7.asd.dao.Database;
import com.group7.asd.dao.UserDBManager;
import com.group7.asd.model.OrderDetail;
import com.group7.asd.model.OrderInformation;
import com.group7.asd.model.Product;
import com.group7.asd.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

// Test add staff
public class OrderTest {
    @Test
    public void testAddOrder() {
        DBConnector db;
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection conn = db.openConnection();
        UserDBManager manager = null;
        try {
            manager = new UserDBManager(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String orderDetail =  "[{\"id\":2,\"pro_name\":\"fish\",\"pro_num\":\"2\",\"pro_purity\":\"fish\",\"pro_service\":\"\",\"pro_price\":100,\"select\":true},{\"id\":3,\"pro_name\":\"hamburger\",\"pro_num\":\"1\",\"pro_purity\":\"fish\",\"pro_service\":\"\",\"pro_price\":10,\"select\":true}]\n" + "";
        JSONArray jsonArray= JSONArray.parseArray(orderDetail);
        String orderNo = UUID.randomUUID().toString();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object = (JSONObject) jsonArray.get(i);
            try {
                manager.saveOrderDetail(object,orderNo);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String userId = "6789";
        Double money = Double.parseDouble("210");
       /* try {
            //manager.saveOrder(orderNo,money,userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

    }

    @Test
    public void selectPersonalOrder() throws SQLException, ClassNotFoundException {
        DBConnector db = new DBConnector();
        Connection conn = db.openConnection();
        UserDBManager manager = new UserDBManager(conn);
        String orderNo = "";
        Map<String, Object> result = new HashMap<String, Object>();
        String userId = "17";
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
        for(int i = 0;i<newOrderInformationList.size();i++) {
            System.out.println(newOrderInformationList.get(i));
        }
    }

}
