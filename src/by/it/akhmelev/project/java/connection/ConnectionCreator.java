package by.it.akhmelev.project.java.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static volatile Connection connection = null;
    private static Properties dbSetting=new Properties();

    static {
        try { //регистрируем драйвер
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed())
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:2016/akhmelev?useUnicode=true&characterEncoding=UTF-8",
                    "root",
                    ""
            );
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        Connection cn=getConnection();
        if (!cn.isClosed())
            System.out.println("ok");

    }

}
