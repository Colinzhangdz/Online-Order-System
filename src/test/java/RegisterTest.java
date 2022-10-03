import com.group7.asd.dao.UserDBManager;
import com.group7.asd.model.User;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RegisterTest {

    //Test add user
    @Test
    public void testUser1() throws Exception{
        User user = new User();
        user.setUserId(6666);
        user.setEmail("666@uts.com");
        user.setPassword("1234");
        user.setFullName("Zhoayu Pan");
        user.setPhone("1234567890");
        user.setUserType("staff");
        user.setIsActive(true);

        UserDBManager userDBManager = new UserDBManager();
        userDBManager.addUser(user);

        User userTest = userDBManager.findUser("Zhoayu Pan", "1234");
        assertEquals(1234567890, userTest.getPhone());
    }

    //Test delete user
    @Test
    public void testUser2() throws Exception{
        UserDBManager userDBManager = new UserDBManager();
        userDBManager.deleteUser(6666);

        User userTest = userDBManager.findUser("Zhoayu Pan", "1234");
        assertNull(userTest.getUserId());
    }
}
