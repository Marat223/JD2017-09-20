package by.it.mustaphin.project.java.dao;

import by.it.mustaphin.project.java.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstactDAO {

    protected int executeCreate(String sql) throws SQLException {
	int result = -1;
	try (Connection con = ConnectionCreator.getConnection(); Statement st = con.createStatement();) {
	    if ((st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)) == 1) {
		ResultSet rs = st.getGeneratedKeys();
		rs.next();
		result = rs.getInt(1);
	    }
	}
	return result;
    }

    protected boolean executeUpdate(String sql) throws SQLException {
	try (Connection con = ConnectionCreator.getConnection(); Statement st = con.createStatement()) {
	    return 1 == st.executeUpdate(sql);
	}
    }
}
