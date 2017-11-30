package by.it.malyshev.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class B_ShowUsers {

    public static void main(String[] args) {

        try (Connection connection =
                     DriverManager.getConnection
                             (CN.URL_DB, CN.USER_DB, CN.PASSWORD_DB);
             Statement statement = connection.createStatement()) {

            ResultSet roles = statement.executeQuery("select * from roles;");

            Map<String, Integer> rolesMap = new HashMap<>();
            Map<String, Integer> usersMap = new HashMap<>();

            while (roles.next()) {
                rolesMap.put(roles.getString("role"), roles.getInt("ID"));
            }

            ResultSet users = statement.executeQuery("select * from users;");
            String uStr = "";
            while (users.next()) {
                uStr = String.format("%-20s %-20s %-20s", users.getString("login"), users.getString("password"), users.getString("email"));
                usersMap.put(uStr, users.getInt("roles_ID"));
            }

            for (Map.Entry<String, Integer> entry : usersMap.entrySet()) {
                for (Map.Entry<String, Integer> rEntry : rolesMap.entrySet()) {
                    if (entry.getValue().equals(rEntry.getValue())) {
                        System.out.println(String.format("%-20s %-20s", entry.getKey(), rEntry.getKey()));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

