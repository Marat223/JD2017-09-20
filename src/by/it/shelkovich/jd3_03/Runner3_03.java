package by.it.shelkovich.jd3_03;

import by.it.shelkovich.jd3_03.entities.User;

import java.sql.SQLException;
import java.util.List;

public class Runner3_03 {
    public static void main(String[] args) {
        UniDAO<User> UserDAO = new UniDAO<>(User.class);
        User user = new User();
        user.setId(1);

        try {
            List<User> users = UserDAO.getAll();
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
