package by.it.ali.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_Table {
    public static void done(){
        try (
                Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);
                Statement statement=connection.createStatement();)
        {
            String createTable = "CREATE TABLE ROLES_ADDITIONAL("
                    + "ID INT(11) NOT NULL AUTO_INCREMENT,"
                    + "Role VARCHAR(20) NOT NULL,"
                    + "PRIMARY KEY (ID)"
                    + ")";



            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
