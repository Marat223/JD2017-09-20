package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.Street;
import by.it.meshchenko.jd03_02.crud.StreetCRUD;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import static org.junit.Assert.assertEquals;

public class StreetCRUDTest {
    @DataPoints
    public static StreetCRUD itemCRUD = new StreetCRUD();
    @DataPoints
    public static Street item = new Street(0, "testStreet", 1);


    Street create() throws Exception {
        Street temp = itemCRUD.create(item);
        item.setId(temp.getId());
        return temp;
    }

    Street read() throws Exception {
        Street temp = itemCRUD.read(StreetCRUDTest.item.getId());
        item.setId(temp.getId());
        return temp;
    }

    Street update() throws Exception {
        item.setName("testStreet#1");
        Street temp = itemCRUD.update(item);
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
