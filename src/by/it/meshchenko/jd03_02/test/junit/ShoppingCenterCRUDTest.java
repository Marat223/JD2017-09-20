package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.ShoppingCenter;
import by.it.meshchenko.jd03_02.crud.ShoppingCenterCRUD;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import static org.junit.Assert.assertEquals;

public class ShoppingCenterCRUDTest {

    @DataPoints
    public static ShoppingCenterCRUD itemCRUD = new ShoppingCenterCRUD();
    @DataPoints
    public static ShoppingCenter item = new ShoppingCenter(0, "testSC", 1,
            null, null,null);


    ShoppingCenter create() throws Exception {
        ShoppingCenter temp = itemCRUD.create(item);
        item.setId(temp.getId());
        return temp;
    }

    ShoppingCenter read() throws Exception {
        ShoppingCenter temp = itemCRUD.read(ShoppingCenterCRUDTest.item.getId());
        item.setId(temp.getId());
        return temp;
    }

    ShoppingCenter update() throws Exception {
        item.setName("testSC#1");
        ShoppingCenter temp = itemCRUD.update(item);
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
