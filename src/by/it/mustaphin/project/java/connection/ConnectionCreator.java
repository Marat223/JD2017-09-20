package by.it.mustaphin.project.java.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {

    private static Connection con = null;
    private static Properties prop = new Properties();

    static {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    prop.load(ConnectionCreator.class.getResourceAsStream("dbConnection.properties"));
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {

	}
    }

    public static Connection getConnection() throws SQLException {
	if (null == con || con.isClosed()) {
	    return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("login"), prop.getProperty("password"));
	}
	return con;
    }

}
