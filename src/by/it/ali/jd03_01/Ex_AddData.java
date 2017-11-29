package by.it.ali.jd03_01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_AddData {
    public static void done() {
        try (
                Connection connection = DriverManager.getConnection(INF.URL, INF.USER, INF.PASSWORD);
                Statement statement=connection.createStatement();)
        {
            statement.executeUpdate("insert into tickets(Destination,Company,Date,Price,Fkusers)"+"values('Paris','S7Airlines','17-08-29','140','3');"
            );
            statement.executeUpdate("insert into tickets(Destination,Company,Date,Price,Fkusers)"+"values('London','Lufthansa','17-12-25','180','3');"
            );
            statement.executeUpdate("insert into tickets(Destination,Company,Date,Price,Fkusers)"+"values('Paris','S7Airlines','17-10-11','190','3');"
            );
            statement.executeUpdate("insert into tickets(Destination,Company,Date,Price,Fkusers)"+"values('Saint-Petersburg','Belavia','17-04-23','100','4');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
