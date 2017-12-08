package by.it.mustaphin.project.java.dao;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class InitBase {

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(System.getProperty("user.dir") + "/src/by/it/mustaphin/jd03_03/connection/dbConnection.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection con = DriverManager.getConnection(prop.getProperty("urlUnsigned"), prop.getProperty("login"), prop.getProperty("password")); Statement st = con.createStatement();) {
            st.executeUpdate("DROP SCHEMA IF EXISTS `mustaphin`;");
            st.executeUpdate("CREATE DATABASE  IF NOT EXISTS `mustaphin` DEFAULT CHARACTER SET utf8;");
            st.executeUpdate("USE `mustaphin`;");
            st.executeUpdate("DROP TABLE IF EXISTS `answers`;");
            st.executeUpdate("DROP TABLE IF EXISTS `questions`;");
            st.executeUpdate("DROP TABLE IF EXISTS `rights`;");
            st.executeUpdate("DROP TABLE IF EXISTS `roles`;");
            st.executeUpdate("DROP TABLE IF EXISTS `users`;");
            st.executeUpdate("CREATE TABLE `roles` (\n" +
                    "  `id_role` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `type` varchar(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`id_role`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            st.executeUpdate("CREATE TABLE `questions` (\n" +
                    "  `id_question` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `theme` varchar(45) NOT NULL DEFAULT 'n/a',\n" +
                    "  PRIMARY KEY (`id_question`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;");
            st.executeUpdate("CREATE TABLE `answers` (\n" +
                    "  `id_answer` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `text` varchar(45) NOT NULL,\n" +
                    "  `fk_question` int(11) NOT NULL,\n" +
                    "  `correct` tinyint(1) DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id_answer`),\n" +
                    "  KEY `fk_answers_questions1_idx` (`fk_question`),\n" +
                    "  CONSTRAINT `fk_answers_questions1` FOREIGN KEY (`fk_question`) REFERENCES `questions` (`id_question`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            st.executeUpdate("CREATE TABLE `users` (\n" +
                    "  `id_user` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `login` varchar(45) NOT NULL,\n" +
                    "  `password` varchar(45) NOT NULL,\n" +
                    "  `fk_question` int(11) NOT NULL DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id_user`),\n" +
                    "  KEY `fk_users_questions1_idx` (`fk_question`),\n" +
                    "  CONSTRAINT `fk_users_questions1` FOREIGN KEY (`fk_question`) REFERENCES `questions` (`id_question`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;");
            st.executeUpdate("CREATE TABLE `rights` (\n" +
                    "  `id_user` int(11) NOT NULL,\n" +
                    "  `admin` tinyint(4) NOT NULL DEFAULT '0',\n" +
                    "  `user` tinyint(4) NOT NULL DEFAULT '0',\n" +
                    "  `guest` tinyint(4) NOT NULL DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id_user`),\n" +
                    "  CONSTRAINT `fk_rights_users1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            st.executeUpdate("INSERT INTO `questions` VALUES (1,'Вводная тема'),(2,'Общие положения'),(3,'Техника безопасности');");
            st.executeUpdate("INSERT INTO `users` VALUES (1,'mustaphin marat','me','1234',2),(2,'ivan petrov','user1','4321',2),(10,'some','any','0000',2);");
//            st.executeUpdate("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
