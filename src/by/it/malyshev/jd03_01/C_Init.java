package by.it.malyshev.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class C_Init {

    public static void main(String[] args) {

        try (Connection connection =
                     DriverManager.getConnection
                             (CN.URL_DBINI, CN.USER_DB, CN.PASSWORD_DB);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP SCHEMA IF EXISTS `malyshev` ;");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS `malyshev` DEFAULT CHARACTER SET utf8 ;");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `malyshev`.`roles` ( " +
                    "`ID` INT NOT NULL AUTO_INCREMENT, " +
                    "`role` VARCHAR(100) NOT NULL, " +
                    "PRIMARY KEY (`ID`)) " +
                    "ENGINE = InnoDB; ");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `malyshev`.`users`( " +
                    "`ID`INT NOT NULL AUTO_INCREMENT, " +
                    "`login`VARCHAR(100) NULL, " +
                    "`password`VARCHAR(100) NULL, " +
                    "`email`VARCHAR(100) NULL, " +
                    "`roles_ID`INT NOT NULL DEFAULT 3, " +
                    "PRIMARY KEY(`ID`), " +
                    "INDEX `fk_users_role_idx`(`roles_ID`ASC), " +
                    "CONSTRAINT `fk_users_role` " +
                    "FOREIGN KEY (`roles_ID`) " +
                    "REFERENCES `malyshev`.`roles`(`ID`) " +
                    "ON DELETE RESTRICT " +
                    "ON UPDATE RESTRICT) " +
                    "ENGINE = InnoDB; ");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `malyshev`.`books`( " +
                    "`ID`INT NOT NULL AUTO_INCREMENT, " +
                    "`title`VARCHAR(100)NOT NULL, " +
                    "`pubyear`YEAR NOT NULL, " +
                    "`isbn`VARCHAR(45)NOT NULL, " +
                    "`price`DECIMAL(10, 2)NOT NULL, " +
                    "PRIMARY KEY(`ID`)) " +
                    "ENGINE = InnoDB; ");


            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `malyshev`.`messages`( " +
                    "`ID`INT NOT NULL AUTO_INCREMENT, " +
                    "`message`VARCHAR(10000)NOT NULL, " +
                    "`users_ID`INT NOT NULL, " +
                    "`books_ID`INT NOT NULL, " +
                    "PRIMARY KEY(`ID`), " +
                    "INDEX `fk_messages_users1_idx` (`users_ID`ASC), " +
                    "INDEX `fk_messages_books1_idx`(`books_ID`ASC), " +
                    "CONSTRAINT `fk_messages_users1` " +
                    "FOREIGN KEY (`users_ID`) " +
                    "REFERENCES `malyshev`.`users`(`ID`) " +
                    "ON DELETE CASCADE " +
                    "ON UPDATE CASCADE, " +
                    "CONSTRAINT `fk_messages_books1` " +
                    "FOREIGN KEY (`books_ID`) " +
                    "REFERENCES `malyshev`.`books`(`ID`) " +
                    "ON DELETE CASCADE " +
                    "ON UPDATE CASCADE) " +
                    "ENGINE = InnoDB; ");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `malyshev`.`orders`( " +
                    "`ID`INT NOT NULL AUTO_INCREMENT, " +
                    "`quantity`INT NOT NULL, " +
                    "`users_ID`INT NOT NULL, " +
                    "`books_ID`INT NOT NULL, " +
                    "PRIMARY KEY(`ID`), " +
                    "INDEX `fk_order_users1_idx` (`users_ID`ASC), " +
                    "INDEX `fk_orders_books1_idx`(`books_ID`ASC), " +
                    "CONSTRAINT `fk_orders_users1` " +
                    "FOREIGN KEY (`users_ID`) " +
                    "REFERENCES `malyshev`.`users`(`ID`) " +
                    "ON DELETE CASCADE " +
                    "ON UPDATE CASCADE, " +
                    "CONSTRAINT `fk_orders_books1` " +
                    "FOREIGN KEY (`books_ID`) " +
                    "REFERENCES `malyshev`.`books`(`ID`) " +
                    "ON DELETE CASCADE " +
                    "ON UPDATE CASCADE) " +
                    "ENGINE = InnoDB; ");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `malyshev`.`store`( " +
                    "`ID`INT NOT NULL AUTO_INCREMENT, " +
                    "`quantity`INT NULL, " +
                    "`books_ID`INT NOT NULL, " +
                    "PRIMARY KEY(`ID`), " +
                    "INDEX `fk_store_books1_idx` (`books_ID`ASC), " +
                    "CONSTRAINT `fk_store_books1` " +
                    "FOREIGN KEY (`books_ID`) " +
                    "REFERENCES `malyshev`.`books`(`ID`) " +
                    "ON DELETE CASCADE " +
                    "ON UPDATE CASCADE) " +
                    "ENGINE = InnoDB; ");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `malyshev`.`authors`( " +
                    "`ID`INT NOT NULL AUTO_INCREMENT, " +
                    "`name`VARCHAR(100)NOT NULL, " +
                    "PRIMARY KEY(`ID`)) " +
                    "ENGINE = InnoDB; ");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `malyshev`.`authors_books`( " +
                    "`authors_ID`INT NOT NULL, " +
                    "`books_ID`INT NOT NULL, " +
                    "PRIMARY KEY(`authors_ID`, `books_ID`), " +
                    "INDEX `fk_Authors_has_books_books1_idx` (`books_ID`ASC), " +
                    "INDEX `fk_Authors_has_books_Authors1_idx`(`authors_ID`ASC), " +
                    "CONSTRAINT `fk_Authors_has_books_Authors1` " +
                    "FOREIGN KEY (`authors_ID`) " +
                    "REFERENCES `malyshev`.`authors`(`ID`) " +
                    "ON DELETE CASCADE " +
                    "ON UPDATE CASCADE, " +
                    "CONSTRAINT `fk_Authors_has_books_books1` " +
                    "FOREIGN KEY (`books_ID`) " +
                    "REFERENCES `malyshev`.`books`(`ID`) " +
                    "ON DELETE NO ACTION " +
                    "ON UPDATE NO ACTION) " +
                    "ENGINE = InnoDB; ");

            statement.executeUpdate("INSERT INTO `malyshev`.`roles`(`ID`, `role`)VALUES(1, 'Administrator'); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`roles`(`ID`, `role`)VALUES(2, 'Guest'); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`users`(`ID`, `login`, `password`, `email`, `roles_ID`) " +
                    "VALUES(1, 'admin', 'padmin', 'admin@it.by', 1); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`users`(`ID`, `login`, `password`, `email`, `roles_ID`) " +
                    "VALUES(2, 'user', 'puser', 'user@it.by', 2); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`users`(`ID`, `login`, `password`, `email`, `roles_ID`) " +
                    "VALUES(3, 'user2', 'puser2', 'user2@it.by', 2); ");

            statement.executeUpdate("INSERT INTO `malyshev`.`books`(`ID`, `title`, `pubyear`, `isbn`, `price`) " +
                    "VALUES(1, 'Java 8. Полное руководство', 2015, '978-5-8459-1918-2', 179.0); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`books`(`ID`, `title`, `pubyear`, `isbn`, `price`) " +
                    "VALUES(2, 'Сильмариллион', 1977, '978-5-17-083893-6', 47.75); ");

            statement.executeUpdate("INSERT INTO `malyshev`.`messages`(`ID`, `message`, `users_ID`, `books_ID`)VALUES(1, 'Хорошая книга', 2, 1); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`messages`(`ID`, `message`, `users_ID`, `books_ID`)VALUES(2, 'Скучная книга', 3, 2); ");

            statement.executeUpdate("INSERT INTO `malyshev`.`orders`(`ID`, `quantity`, `users_ID`, `books_ID`)VALUES(1, 1, 1, 1); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`orders`(`ID`, `quantity`, `users_ID`, `books_ID`)VALUES(2, 2, 2, 2); ");

            statement.executeUpdate("INSERT INTO `malyshev`.`store`(`ID`, `quantity`, `books_ID`)VALUES(1, 15, 1); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`store`(`ID`, `quantity`, `books_ID`)VALUES(2, 3, 2); ");

            statement.executeUpdate("INSERT INTO `malyshev`.`authors`(`ID`, `name`)VALUES(1, 'Герберт Шилдт'); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`authors`(`ID`, `name`)VALUES(2, 'Дж. Р. Р. Толкин'); ");

            statement.executeUpdate("INSERT INTO `malyshev`.`authors_books`(`authors_ID`, `books_ID`)VALUES(1, 1); ");
            statement.executeUpdate("INSERT INTO `malyshev`.`authors_books`(`authors_ID`, `books_ID`)VALUES(2, 2); ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
