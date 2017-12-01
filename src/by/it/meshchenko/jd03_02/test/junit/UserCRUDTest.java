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


public class UserCRUDTest {

    @DataPoints
    public static UserCRUD userCRUD = new UserCRUD();
    @DataPoints
    public static User user = new User(0, "testAdmin", "super",
                null, "test11@sup.su", false,1);


    User create() throws Exception {
        User temp = userCRUD.create(user);
        user.setId(temp.getId());
        return temp;
    }

    User read() throws Exception {
        User temp = userCRUD.read(UserCRUDTest.user.getId());
        user.setId(temp.getId());
        return temp;
    }

    User update() throws Exception {
        user.setName("testAdmin#1");
        User temp = userCRUD.update(user);
        user.setId(temp.getId());
        return temp;
    }

    boolean delete() throws Exception {
        return userCRUD.delete(user);
    }

    @Test
    public void testOrder1() throws Exception {

        assertEquals(true, user.equals(create()));

        assertEquals(true, user.equals(read()));

        assertEquals(true, user.equals(update()));

        assertEquals(true, delete());
    }

}
