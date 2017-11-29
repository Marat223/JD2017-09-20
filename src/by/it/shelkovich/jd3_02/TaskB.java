package by.it.shelkovich.jd3_02;

import by.it.shelkovich.jd3_03.dao.DAO;
import by.it.shelkovich.jd3_03.dao.DataBase;
import by.it.shelkovich.jd3_03.dao.entities.Role;
import by.it.shelkovich.jd3_03.dao.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskB {
    static void run() {
        try {
            ResultSet rs = DataBase.exeSelectQuery("SELECT username, role FROM users LEFT JOIN roles ON users.role_id=roles.id");

            while (rs.next()) {
                System.out.println("User: " + rs.getString(1) + ", role: " + rs.getString(2));
            }

            System.out.println("Всего пользователей: "+DataBase.exeSingleFieldSelectQuery("SELECT COUNT(*) as count FROM users", "count"));
            System.out.println("Всего ролей: "+DataBase.exeSingleFieldSelectQuery("SELECT COUNT(*) as count FROM roles", "count"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
