package by.it.ali.jd03_02;

import by.it.ali.jd03_01.INF;

import java.sql.*;

public class Create_Read {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);
                Statement statement=connection.createStatement();)
        {
            statement.executeUpdate("insert into users(Login,Password,Email,FKroles, FKroles_additional)"+"values('Sarah','Moonie','SarahMoon@gmail.com','1','1');"
            );
            statement.executeUpdate("insert into users(Login,Password,Email,FKroles, FKroles_additional)"+"values('Donald','biggie','DonaldD@gmail.com','2','2');"
            );
            ResultSet resultSet=statement.executeQuery("SELECT* from users");
            while (resultSet.next())
                System.out.println(resultSet.getString("Login")+", "+resultSet.getString("Password")+", "+resultSet.getString("Email")+", "+resultSet.getString("FKroles")+", "+resultSet.getString("FKroles_additional"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
