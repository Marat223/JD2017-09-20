package by.it.meshchenko.jd03_02.crud;

import by.it.meshchenko.jd03_02.*;
import by.it.meshchenko.jd03_02.beans.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserCRUD {

    public User create(User user) throws SQLException {
        user.setId(0);
        String createUserSQL = String.format(
                "INSERT INTO crm_users(" +
                        "Name,Password,%sEmail,EmailConfirmed%s)" +
                        " VALUES('%s','%s',%s'%s',%d%s);",
                user.getPhoneNumber() == null ? "" : "PhoneNumber,",
                user.getRoleId() == null ? "" : ",RoleId",
                user.getName(), //PhoneNumber
                user.getPassword(),
                user.getPhoneNumber() == null ? "" : "\'" + user.getPhoneNumber().get() + "\',",
                user.getEmail(),
                user.isEmailConfirmed() ? 1 : 0,
                user.getRoleId() == null ? "" : "," + user.getRoleId().get()
        );
        System.out.println(createUserSQL);
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            //выполняем добавление в базу, должна быть добавлена одна запись. Проверим это.
            //create(insert) update delete - это executeUpdate, а select это executeQuery
            if (1 == statement.executeUpdate(createUserSQL, RETURN_GENERATED_KEYS)) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    user.setId(id);
                }
            }
        }
        return user;
    }

    public User read(int id) throws SQLException {
        User userResult = null;
        Object tempObj;
        String readUserSQL = "SELECT * FROM crm_users WHERE ID=" + id;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readUserSQL);
            if (resultSet.next()) {
                userResult = new User(
                        resultSet.getInt("Id"),
                        resultSet.getString("Name"),
                        resultSet.getString("Password"),
                        (tempObj = resultSet.getObject("PhoneNumber")) == null
                                ? null : Optional.of(tempObj.toString()),
                        resultSet.getString("Email"),
                        resultSet.getBoolean("EmailConfirmed"),
                        (tempObj = resultSet.getObject("RoleId")) == null
                                ? null : Optional.of(Integer.valueOf(tempObj.toString())));
            }
        }
        return userResult;
    }

    public User update(User user) throws SQLException {
        User userResult = null;
        //String temp = "";
        String updateUserSQL = String.format(
                "UPDATE crm_users SET " +
                        "Name = '%s', Password = '%s',%s Email = '%s', " +
                        "EmailConfirmed = %d%s WHERE Id = %d",
                user.getName(),
                user.getPassword(),
                user.getPhoneNumber() == null ? "" : "PhoneNumber=\'" + user.getPhoneNumber().get() + "\',",
                user.getEmail(),
                user.isEmailConfirmed() ? 1 : 0,
                user.getRoleId() == null ? "" : ",RoleId=" + user.getRoleId().get().toString(),
                user.getId()
        );
        System.out.println(updateUserSQL);
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.executeUpdate(updateUserSQL) == 1)
                userResult = user;
        }
        return userResult;
    }

    public boolean delete(User user) throws SQLException {
        String deleteUserSQL = String.format("DELETE FROM crm_users WHERE Id = %d", user.getId());
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            return (statement.executeUpdate(deleteUserSQL) == 1);
        }
    }
}
