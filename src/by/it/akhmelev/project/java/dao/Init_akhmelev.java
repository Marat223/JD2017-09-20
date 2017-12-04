package by.it.akhmelev.project.java.dao;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Init_akhmelev {

    static final String URL_DB = "jdbc:mysql://127.0.0.1:2016/?useUnicode=true&characterEncoding=UTF-8";
    static final String USER_DB = "root";
    static final String PASSWORD_DB = "";


    public static void main(String[ ] args) {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection=
                     DriverManager.getConnection
                             (URL_DB, USER_DB, PASSWORD_DB);
             Statement statement=connection.createStatement()) {
            //пересоздание базы целиком
            statement.executeUpdate("DROP SCHEMA IF EXISTS `akhmelev` ;");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS `akhmelev` DEFAULT CHARACTER SET utf8 ;");
            //удаление всех таблиц (если работает удаление схемы то это лишнее)
            statement.executeUpdate("DROP TABLE IF EXISTS `akhmelev`.`ads`;");
            statement.executeUpdate("DROP TABLE IF EXISTS `akhmelev`.`users`;");
            statement.executeUpdate("DROP TABLE IF EXISTS `akhmelev`.`roles`;");
            //генерация всех таблиц
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `akhmelev`.`roles` (\n" +
                            "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `Role` VARCHAR(45) NULL,\n" +
                            "  PRIMARY KEY (`ID`))\n" +
                            "ENGINE = InnoDB;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `akhmelev`.`users` (\n" +
                            "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `Login` VARCHAR(45) NULL,\n" +
                            "  `Password` VARCHAR(45) NULL,\n" +
                            "  `Email` VARCHAR(45) NULL,\n" +
                            "  `FK_roles` INT NOT NULL,\n" +
                            "  PRIMARY KEY (`ID`),\n" +
                            "  INDEX `fk_users_FK_rolesx` (`FK_roles` ASC),\n" +
                            "  CONSTRAINT `fk_users_roles`\n" +
                            "    FOREIGN KEY (`FK_roles`)\n" +
                            "    REFERENCES `akhmelev`.`roles` (`ID`)\n" +
                            "    ON DELETE RESTRICT \n" +
                            "    ON UPDATE RESTRICT )\n" +
                            "ENGINE = InnoDB;"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `akhmelev`.`ads` (\n" +
                            "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `RoomCount` INT NULL,\n" +
                            "  `Floor` INT NULL,\n" +
                            "  `Floors` INT NULL,\n" +
                            "  `Price` DOUBLE NULL,\n" +
                            "  `Area` DOUBLE NULL,\n" +
                            "  `Description` VARCHAR(5000) NULL,\n" +
                            "  `Address` VARCHAR(200) NULL,\n" +
                            "  `FK_users` INT NOT NULL,\n" +
                            "  PRIMARY KEY (`ID`),\n" +
                            "  INDEX `fk_ads_users1_idx` (`FK_users` ASC),\n" +
                            "  CONSTRAINT `fk_ads_users1`\n" +
                            "    FOREIGN KEY (`FK_users`)\n" +
                            "    REFERENCES `akhmelev`.`users` (`ID`)\n" +
                            "    ON DELETE CASCADE\n" +
                            "    ON UPDATE CASCADE)\n" +
                            "ENGINE = InnoDB;"
            );
            //вставка тестовых записей
            statement.executeUpdate("INSERT INTO `akhmelev`.`roles` (`ID`, `Role`) VALUES (DEFAULT, 'Administrator');");
            statement.executeUpdate("INSERT INTO `akhmelev`.`roles` (`ID`, `Role`) VALUES (DEFAULT, 'User');");
            statement.executeUpdate("INSERT INTO `akhmelev`.`roles` (`ID`, `Role`) VALUES (DEFAULT, 'Guest');");
            statement.executeUpdate("INSERT INTO `akhmelev`.`users` (`ID`, `Login`, `Password`, `Email`, `FK_roles`) VALUES (DEFAULT, 'admin', 'padmin', 'admin@it.by', 1);");
            statement.executeUpdate("INSERT INTO `akhmelev`.`users` (`ID`, `Login`, `Password`, `Email`, `FK_roles`) VALUES (DEFAULT, 'user', 'puser', 'user@it.by', 2);");
            statement.executeUpdate("INSERT INTO `akhmelev`.`ads` (`ID`, `RoomCount`, `Floor`, `Floors`, `Price`, `Area`, `Description`, `Address`, `FK_users`) VALUES (DEFAULT, '2', 4, 5, 55555, 45, 'Хорошая квартира', 'Кнорина 2, кв. 11', 2);");

            System.out.println("DB akhmelev. Init complete.");
        }
        catch (Exception e){
            System.err.println("DB akhmelev. Init fail.\n\n"+e.getMessage());
        }
    }
}
