package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.LeaseRoom;
import by.it.meshchenko.jd03_02.crud.LeaseRoomCRUD;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import static org.junit.Assert.assertEquals;

public class LeaseRoomCRUDTest {

    @DataPoints
    public static LeaseRoomCRUD itemCRUD = new LeaseRoomCRUD();
    @DataPoints
    public static LeaseRoom item = new LeaseRoom(0, null, null,
            null, 1,1);


    LeaseRoom create() throws Exception {
        LeaseRoom temp = itemCRUD.create(item);
        item.setId(temp.getId());
        return temp;
    }

    LeaseRoom read() throws Exception {
        LeaseRoom temp = itemCRUD.read(LeaseRoomCRUDTest.item.getId());
        item.setId(temp.getId());
        return temp;
    }

    LeaseRoom update() throws Exception {
        item.setPrice(750.5f);
        LeaseRoom temp = itemCRUD.update(item);
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
