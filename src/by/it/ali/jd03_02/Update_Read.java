package by.it.ali.jd03_02;

import by.it.ali.jd03_01.INF;

import java.sql.*;

public class Update_Read {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);
                Statement statement=connection.createStatement();)
        {
            statement.executeUpdate("UPDATE users SET Login='1111111111111', Password = '1111111111111', Email = '11111111111111' WHERE ID=4;"
            );
            ResultSet resultSet=statement.executeQuery("SELECT* from users");
            while (resultSet.next())
                System.out.println(resultSet.getString("Login")+", "+resultSet.getString("Password")+", "+resultSet.getString("Email")+", "+resultSet.getString("FKroles")+", "+resultSet.getString("FKroles_additional"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
