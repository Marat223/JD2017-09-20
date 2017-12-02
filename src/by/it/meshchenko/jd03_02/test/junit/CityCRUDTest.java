package by.it.meshchenko.jd03_02.test.junit;

import by.it.meshchenko.jd03_02.beans.City;
import by.it.meshchenko.jd03_02.crud.CityCRUD;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

import static org.junit.Assert.assertEquals;

public class CityCRUDTest {

    @DataPoints
    public static CityCRUD itemCRUD = new CityCRUD();
    @DataPoints
    public static City item = new City(0, "Gomel", 1);


    City create() throws Exception {
        City temp = itemCRUD.create(item);
        item.setId(temp.getId());
        return temp;
    }

    City read() throws Exception {
        City temp = itemCRUD.read(CityCRUDTest.item.getId());
        item.setId(temp.getId());
        return temp;
    }

    City update() throws Exception {
        item.setName("testAdmin#1");
        City temp = itemCRUD.update(item);
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
