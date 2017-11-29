package by.it.ali.jd03_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_AdditionalRoles {
    public static void done(){
        try (
                Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);
                Statement statement=connection.createStatement();)
        {
//            statement.executeUpdate("insert into users(FKroles)"+"values('3');"
//            );
//            statement.executeUpdate("insert into users(FKroles)"+"values('3');"
//            );
            statement.executeUpdate("insert into users(Login,Password,Email,FKroles, FKroles_additional)"+"values('Sarah','qwerty12','SarahMoll@gmail.com','1','1');"
            );
            statement.executeUpdate("insert into users(Login,Password,Email,FKroles, FKroles_additional)"+"values('Donald','biggie','DonaldD@gmail.com','2','2');"
            );
                        statement.executeUpdate("insert into users(FKroles)"+"values('3');"
            );
            statement.executeUpdate("insert into users(FKroles)"+"values('3');"
            );
//            statement.executeUpdate("insert into users(Login,Password,Email,FKroles)"+"values('Sarah','qwerty12','SarahMoll@gmail.com','2');"
//            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
