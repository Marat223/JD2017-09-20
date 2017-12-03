package by.it.malyshev.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class C_Reset {

    public static void main(String[] args) {

        try (Connection connection =
                     DriverManager.getConnection
                             (CN.URL_DB, CN.USER_DB, CN.PASSWORD_DB);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS `malyshev`.`messages` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `malyshev`.`store` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `malyshev`.`authorsBooks` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `malyshev`.`authors` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `malyshev`.`orders` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `malyshev`.`users` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `malyshev`.`books` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `malyshev`.`roles` ;");
            statement.executeUpdate("DROP SCHEMA IF EXISTS `malyshev` ;");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
