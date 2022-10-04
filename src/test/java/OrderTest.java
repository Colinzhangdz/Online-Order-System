import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group7.asd.dao.DBConnector;
import com.group7.asd.dao.Database;
import com.group7.asd.dao.UserDBManager;
import com.group7.asd.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

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
        try {
            manager.saveOrder(orderNo,money,userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
