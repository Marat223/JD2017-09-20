package by.it.shelkovich.jd3_03.dao;

import by.it.shelkovich.jd3_03.dao.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class CRUDTest {
    @Test
    public void crud() throws Exception {
        DAO dao = DAO.getInstance();
        User user = new User();
        User newUser;
        user.setId(0L);
        user.setUsername("petrovich");
        user.setEmail("petrovich@gmail.com");
        user.setRole_id(2L);
        user.setPassHash("123456");
        user.setShowPersonalData(false);

        dao.userDAO.create(user);
        newUser = dao.userDAO.read(user.getId());
        assertEquals(user, newUser);

        user.setDescription("Сантехник от бога!");
        dao.userDAO.update(user);
        newUser = dao.userDAO.read(user.getId());
        assertEquals(user, newUser);

        boolean result = dao.userDAO.delete(user.getId());
        assertEquals(true, result);

        newUser = dao.userDAO.read(user.getId());
        assertEquals(null, newUser);
    }


}