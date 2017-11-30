package by.it.govor.bigBossProject.dao;


import by.it.govor.bigBossProject.ConnectionCreator;
import by.it.govor.bigBossProject.table.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractDAO {
    boolean executeUpdate(String updateSQL) throws SQLException {
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
         return   (statement.executeUpdate(updateSQL) == 1);
        }
    }
    int executeCreate(String sql) throws SQLException {
        int result=-1;
        try (Connection connection=ConnectionCreator.getConnection();
             Statement statement=connection.createStatement();){
            if (statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS)==1){
                ResultSet keys=statement.getGeneratedKeys();
                keys.next();
                result=keys.getInt(1);
            }
        }
        return result;
    }
}
