package by.it.ali.jd03_02;

import by.it.ali.jd03_01.INF;

import java.sql.*;

public class TaskB {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);
                Statement statement=connection.createStatement();)
        {
            ResultSet resultSet=statement.executeQuery("SELECT Login,FKroles,(SELECT Role from roles where ID=FKroles) from users");
            while (resultSet.next())
                System.out.println(resultSet.getString("Login")+", "+resultSet.getString("FKroles")+", "+resultSet.getString("Role"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
