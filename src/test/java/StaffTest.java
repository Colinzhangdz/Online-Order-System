import com.group7.asd.dao.Database;
import com.group7.asd.dao.UserDBManager;
import com.group7.asd.model.Staff;
import com.group7.asd.model.User;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test add staff
public class StaffTest {
    @Test
    public void testAddStaff() throws Exception{
    ArrayList<Staff> staffs = Database.instance().getStaffs();
    Staff staff = new Staff();
        staff.setAge("30");
        staff.setGender("male");
        staff.setName("Test Name");
        staff.setPassword("1234");
        staff.setPhone("11111111111");
        Database.instance().addStaff(staff);
        ArrayList<Staff> staffs2 = Database.instance().getStaffs();
        Assertions.assertEquals(1, staffs2.size() - staffs.size());
    }
}
