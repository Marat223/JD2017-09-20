package by.it.meshchenko.jd03_02.test;

import by.it.meshchenko.jd03_02.beans.*;
import by.it.meshchenko.jd03_02.crud.*;
import java.sql.SQLException;
import java.util.Optional;

public class UserTest {
    public static void main(String[] args) {
        User user = new User(0, "superAdmin", "super",
                null, "super@sup.su", false,
                Optional.of(new Integer(1)));
        try {
            //проверка CRUD для пользователей
            UserCRUD userCRUD = new UserCRUD();

            //создание
            user=userCRUD.create(user);
            System.out.print("create: "+user);

            //чтение
            System.out.print("read: "+userCRUD.read(user.getId()));

            //обновление
            user.setName("Admin#1");
            user=userCRUD.update(user);
            System.out.print("update: " + userCRUD.read(user.getId()));

            //удаление
            if (userCRUD.delete(user))
                System.out.print("delete: " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
