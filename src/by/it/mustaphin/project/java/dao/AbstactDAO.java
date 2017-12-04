package by.it.mustaphin.project.java.dao;

import by.it.mustaphin.project.java.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstactDAO {

    protected int executeUpdate(String sql) {
        int result = -1;
        try (Connection con = ConnectionCreator.getConnection()) {
            Statement st = con.createStatement();
            result = st.executeUpdate(sql);
            if (sql.trim().startsWith("INSERT")) {
                ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

}
