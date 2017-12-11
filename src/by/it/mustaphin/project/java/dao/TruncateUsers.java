/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.it.mustaphin.project.java.dao;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author me
 */
public class TruncateUsers {

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "java" + File.separator + "by" + File.separator + "it" + File.separator + "mustaphin" + File.separator + "project" + File.separator + "java" + File.separator + "connection" + File.separator + "dbConnection.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("login"), prop.getProperty("password")); Statement st = con.createStatement();) {
            st.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
            st.executeUpdate("TRUNCATE TABLE users;");
            st.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
