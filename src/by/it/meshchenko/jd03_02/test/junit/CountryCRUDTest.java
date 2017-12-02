package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.Country;
import by.it.meshchenko.jd03_02.crud.CountryCRUD;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import static org.junit.Assert.assertEquals;

public class CountryCRUDTest {
    @DataPoints
    public static CountryCRUD itemCRUD = new CountryCRUD();
    @DataPoints
    public static Country item = new Country(0, "Russia");


    Country create() throws Exception {
        Country temp = itemCRUD.create(item);
        item.setId(temp.getId());
        return temp;
    }

    Country read() throws Exception {
        Country temp = itemCRUD.read(CountryCRUDTest.item.getId());
        item.setId(temp.getId());
        return temp;
    }

    Country update() throws Exception {
        item.setName("Russia#1");
        Country temp = itemCRUD.update(item);
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
