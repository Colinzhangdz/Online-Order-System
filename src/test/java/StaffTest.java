import com.group7.asd.dao.Database;
//import com.group7.asd.dao.UserDBManager;
import com.group7.asd.model.Staff;
//import com.group7.asd.model.User;

import org.junit.jupiter.api.*;
import org.junit.Test;
import java.util.ArrayList;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffTest {
    @Test
    public void testAddStaff(){
    ArrayList<Staff> staffs = Database.instance().getStaffs();
    Staff staff = new Staff();
        staff.setUserType("staff");
        staff.setFullName("Test Name");
        staff.setPassword("1234");
        staff.setPhone("11111111111");
        Database.instance().addStaff(staff);
        ArrayList<Staff> staffs2 = Database.instance().getStaffs();
        Assertions.assertEquals(1, staffs2.size() - staffs.size());
    }
}
