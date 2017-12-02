package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.Role;
import by.it.meshchenko.jd03_02.crud.RoleCRUD;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import static org.junit.Assert.assertEquals;

public class RoleCRUDTest {

    @DataPoints
    public static RoleCRUD itemCRUD = new RoleCRUD();
    @DataPoints
    public static Role item = new Role(0, "testRole");


    Role create() throws Exception {
        Role temp = itemCRUD.create(item);
        item.setId(temp.getId());
        return temp;
    }

    Role read() throws Exception {
        Role temp = itemCRUD.read(RoleCRUDTest.item.getId());
        item.setId(temp.getId());
        return temp;
    }

    Role update() throws Exception {
        item.setRole("testRole#1");
        Role temp = itemCRUD.update(item);
        item.setId(temp.getId());
        return temp;
    }

    boolean delete() throws Exception {
        return itemCRUD.delete(item);
    }

    @Test
    public void testOrder1() throws Exception {

        assertEquals(true, item.equals(create()));

        assertEquals(true, item.equals(read()));

        assertEquals(true, item.equals(update()));

        assertEquals(true, delete());
    }
}
