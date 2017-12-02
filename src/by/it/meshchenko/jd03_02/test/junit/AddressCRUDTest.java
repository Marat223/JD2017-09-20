package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.Address;
import by.it.meshchenko.jd03_02.crud.AddressCRUD;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import static org.junit.Assert.assertEquals;

public class AddressCRUDTest {

    @DataPoints
    public static AddressCRUD itemCRUD = new AddressCRUD();
    @DataPoints
    public static Address item = new Address(0, 1, "77");


    Address create() throws Exception {
        Address temp = itemCRUD.create(item);
        item.setId(temp.getId());
        return temp;
    }

    Address read() throws Exception {
        Address temp = itemCRUD.read(AddressCRUDTest.item.getId());
        item.setId(temp.getId());
        return temp;
    }

    Address update() throws Exception {
        item.setBuildingNumberStr("88");
        Address temp = itemCRUD.update(item);
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
