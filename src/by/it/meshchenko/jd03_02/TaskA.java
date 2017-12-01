package by.it.meshchenko.jd03_02;

import java.sql.*;

public class TaskA {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionCreator.getConnection();
        Statement statement = connection.createStatement();

        System.out.println("Role = amdin, id = " + getRoleIdByRole("admin",statement));
        System.out.println("Role = user, id = " + getRoleIdByRole("user",statement));
        System.out.println("Role = manager, id = " + getRoleIdByRole("manager",statement));
    }

    private static int getRoleIdByRole(String role, Statement statement) throws SQLException{
        int id = -1;
        ResultSet res = statement.executeQuery(
                String.format("SELECT Id FROM crm_roles WHERE Role = '%s';", role));
        if(res.next()) {
            id = res.getInt(1);
        }
        return id;
    }
}


