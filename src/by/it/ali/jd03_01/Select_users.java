package by.it.ali.jd03_01;

import by.it.ali.jd03_01.INF;

import java.sql.*;

public class Select_users {
    public static void done() {
        try (
                Connection connection = DriverManager.getConnection(INF.URL,INF.USER,INF.PASSWORD);
                Statement statement = connection.createStatement();)
        {
            ResultSet resultSet=statement.executeQuery("select*from users");
            while (resultSet.next()){
                String out=resultSet.getString("Login")+", "+resultSet.getString("Email")+", "+resultSet.getString("FKroles")+", "+resultSet.getString("FKroles_additional");
                System.out.println(out);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
