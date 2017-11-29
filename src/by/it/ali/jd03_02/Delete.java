package by.it.ali.jd03_02;

import by.it.ali.jd03_01.INF;

import java.sql.*;

public class Delete {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);
                Statement statement=connection.createStatement();)
        {
            statement.executeUpdate("DELETE FROM users WHERE ID=5;"
            );
            ResultSet resultSet=statement.executeQuery("SELECT* from users");
            while (resultSet.next())
                System.out.println(resultSet.getString("Login")+", "+resultSet.getString("Password")+", "+resultSet.getString("Email")+", "+resultSet.getString("FKroles")+", "+resultSet.getString("FKroles_additional"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
