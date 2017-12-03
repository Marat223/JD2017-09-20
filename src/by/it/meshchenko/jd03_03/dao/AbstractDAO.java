package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.connection.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {

    protected int executeCreate(String sql) {
        int result = -1;
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement();){
            if (statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS) == 1){
                ResultSet keys = statement.getGeneratedKeys();
                keys.next();
                result = keys.getInt(1);
            }
        }
        catch (SQLException e) {
            //тут нужно логгирование SQLException(e);
        }
        return result;
    }

    protected boolean executeUpdate(String sql) {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()){
            return 1 == statement.executeUpdate(sql);
        }
        catch (SQLException e) {
        //тут нужно логгирование SQLException(e);
        }
        return false;
    }
}
