package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.Room;
import by.it.meshchenko.jd03_02.crud.RoomCRUD;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import static org.junit.Assert.assertEquals;

public class RoomCRUDTest {
    @DataPoints
    public static RoomCRUD itemCRUD = new RoomCRUD();
    @DataPoints
    public static Room item = new Room(0, "testName", null,1);


    Room create() throws Exception {
        Room temp = itemCRUD.create(item);
        item.setId(temp.getId());
        return temp;
    }

    Room read() throws Exception {
        Room temp = itemCRUD.read(RoomCRUDTest.item.getId());
        item.setId(temp.getId());
        return temp;
    }

    Room update() throws Exception {
        item.setName("testAdmin#1");
        Room temp = itemCRUD.update(item);
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
