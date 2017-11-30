package by.it.malyshev.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class B_AddRoles {

    public static void main(String[] args) {

        try (Connection connection =
                     DriverManager.getConnection
                             (CN.URL_DB, CN.USER_DB, CN.PASSWORD_DB);
             Statement statement = connection.createStatement()) {

            int moderator = 0, user = 0;

            if (1 == statement.executeUpdate("insert into roles(`role`) values('Moderator');",
                    Statement.RETURN_GENERATED_KEYS)) {
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    moderator = key.getInt(1);
                }
            }


            if (1 == statement.executeUpdate("insert into roles(`role`) values('User');",
                    Statement.RETURN_GENERATED_KEYS)) {
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    user = key.getInt(1);
                }
            }


            statement.executeUpdate(String.format(
                    "insert into users(`login`,`password`,`email`,`roles_ID`)" +
                            " values('moderator','moder','moderator@gmail.com',%d);", moderator));


            statement.executeUpdate("update users set `roles_ID` = " + user
                    + " where email = 'user@it.by';");
            statement.executeUpdate("update users set `roles_ID` = " + user
                    + " where email = 'user2@it.by';");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
