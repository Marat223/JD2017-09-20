package by.it.meshchenko.jd03_02.crud;

import by.it.meshchenko.jd03_02.ConnectionCreator;
import by.it.meshchenko.jd03_02.beans.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class RoleCRUD {

    public Role create(Role item) throws SQLException {
        item.setId(0);
        String createItemSQL = String.format(
                "INSERT INTO crm_roles(" +
                        "Role) " +
                        "VALUES('%s');",
                item.getRole()
        );
        System.out.println(createItemSQL);
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            //выполняем добавление в базу, должна быть добавлена одна запись. Проверим это.
            //create(insert) update delete - это executeUpdate, а select это executeQuery
            if (1 == statement.executeUpdate(createItemSQL, RETURN_GENERATED_KEYS)) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    item.setId(id);
                }
            }
        }
        return item;
    }

    public Role read(int id) throws SQLException {
        Role itemResult = null;
        Object tempObj;
        String readItemSQL = "SELECT * FROM crm_roles WHERE ID=" + id;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readItemSQL);
            if (resultSet.next()) {
                itemResult = new Role(
                        resultSet.getInt("Id"),
                        resultSet.getString("Role"));
            }
        }
        return itemResult;
    }

    public Role update(Role item) throws SQLException {
        Role itemResult = null;
        //String temp = "";
        String updateItemSQL = String.format(
                "UPDATE crm_roles SET " +
                        "Role = '%s' WHERE Id = %d",
                item.getRole(),
                item.getId()
        );
        System.out.println(updateItemSQL);
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.executeUpdate(updateItemSQL) == 1)
                itemResult = item;
        }
        return itemResult;
    }

    public boolean delete(Role item) throws SQLException {
        String deleteItemSQL = String.format("DELETE FROM crm_roles WHERE Id = %d", item.getId());
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            return (statement.executeUpdate(deleteItemSQL) == 1);
        }
    }
}
