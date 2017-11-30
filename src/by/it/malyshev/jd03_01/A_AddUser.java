package by.it.malyshev.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class A_AddUser {

    public static void main(String[] args) {

        try (Connection connection =
                     DriverManager.getConnection
                             (CN.URL_DB, CN.USER_DB, CN.PASSWORD_DB);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(
                    "insert into users(`login`,`password`,`email`,`roles_ID`)" +
                            " values('TestAdmin','admin2','admin2@gmail.com',1);"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
