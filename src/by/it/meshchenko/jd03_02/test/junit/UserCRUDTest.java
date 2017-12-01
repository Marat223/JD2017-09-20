package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.User;
import by.it.meshchenko.jd03_02.crud.UserCRUD;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCRUDTest {


    //private static UserCRUD userCRUD;
    //private static User user;
    @DataPoints
    public static UserCRUD userCRUD = new UserCRUD();
    @DataPoints
    public static User user = new User(0, "testAdmin", "super",
                null, "test9@sup.su", false,
                Optional.of(new Integer(1)));


    @Test
    public void create() throws Exception {
        User temp = UserCRUDTest.userCRUD.create(user);
        UserCRUDTest.user.setId(temp.getId());
        System.out.println("id=" + UserCRUDTest.user.getId() );
        assertEquals(true, temp.equals(UserCRUDTest.user));
    }

    @Test
    public void read() throws Exception {
        System.out.println("id=" + UserCRUDTest.user.getId() );
        User temp = UserCRUDTest.userCRUD.read(UserCRUDTest.user.getId());
        UserCRUDTest.user.setId(temp.getId());
        assertEquals(true, temp.equals(UserCRUDTest.user));
    }

    @Test
    public void update() throws Exception {
        user.setName("testAdmin#1");
        User temp = userCRUD.update(user);
        user.setId(temp.getId());
        assertEquals(true, temp.equals(user));
    }

    @Test
    public void delete() throws Exception {
        assertEquals(true, userCRUD.delete(user));
    }

}
