package by.it.ali.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex_Connection {
    public static void main(String[] args) {
        try (
            Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);){
            if (!connection.isClosed())
                System.out.println("Connected!");
            connection.close();
            if (connection.isClosed())
                System.out.println("Disconnected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
