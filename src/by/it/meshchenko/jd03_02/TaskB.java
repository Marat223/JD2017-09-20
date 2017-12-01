package by.it.meshchenko.jd03_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaskB {
    public static void main(String[] args) {
        int id = 0;
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {
            // пользователи с их текстовыми ролями
            System.out.println("1. пользователи с их текстовыми ролями");
            ResultSet res = statement.executeQuery(
                    "SELECT u.Name, u.Email, r.Role " +
                        "FROM crm_roles r " +
                        "INNER JOIN crm_users u " +
                        "ON r.Id = u.RoleId;");

            while(res.next()) {
                String out = String.format("%-32s %-15s %-15s",
                        res.getString("Email"),
                        res.getString("Name"),
                        res.getString("Role"));
                System.out.println(out);
            }

            // общее число пользователей
            System.out.println("2.");
            res = statement.executeQuery(
                    "SELECT COUNT(Email) FROM crm_users ;");

            if(res.next()) {
                String out = String.format("Общее число пользователей: %d",
                        res.getInt(1));
                System.out.println(out);
            }

            //числом возможных ролей
            System.out.println("3. числом возможных ролей");
            res = statement.executeQuery(
                    "SELECT r.Role, COUNT(r.Id) " +
                            "FROM crm_roles r " +
                            "INNER JOIN crm_users u " +
                            "ON r.Id = u.RoleId " +
                            "GROUP BY r.Role;");

            while(res.next()) {
                String out = String.format("%-15s %d",
                        res.getString("Role"),
                        res.getInt(2));
                System.out.println(out);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
