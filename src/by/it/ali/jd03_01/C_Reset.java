package by.it.ali.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class C_Reset {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);
                Statement statement=connection.createStatement();)
        {
            String dropTable = "DROP DATABASE ali";
            statement.executeUpdate(dropTable);
            System.out.println("Database deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
