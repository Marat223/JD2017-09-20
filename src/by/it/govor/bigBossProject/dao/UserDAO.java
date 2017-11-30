package by.it.govor.bigBossProject.dao;


import by.it.govor.bigBossProject.ConnectionCreator;
import by.it.govor.bigBossProject.crud.ICRUD;
import by.it.govor.bigBossProject.table.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends AbstractDAO implements ICRUD<User> {
    @Override
    public boolean create(User user) throws SQLException {
        String createUserSQL = String.format(
                "insert into user(Login,Password,Name,Nick,Telephone,role_ID) values('%s','%s','%s','%s',%d,%d);",
                user.getLogin(), user.getPassword(), user.getName(), user.getNick(), user.getTelephone(), user.getRole_ID()
        );
        int id=executeCreate(createUserSQL);
        if (id>0) user.setId(id);
        return (id>0);
    }

    @Override
    public boolean update(User user) throws SQLException {
        String updateUserSQL = String.format(
                "UPDATE user SET Login = '%s', Password = '%s', Name = '%s', Nick = '%s', Telephone = '%d',role_ID=%d WHERE user.ID = %d",
                user.getLogin(), user.getPassword(),   user.getName(), user.getNick(), user.getTelephone(), user.getRole_ID(),user.getId()
        );
        return executeUpdate(updateUserSQL);
    }

    @Override
    public boolean delete(User user) throws SQLException {
        String deleteUserSQL = String.format("DELETE FROM user WHERE user.ID = %d", user.getId());
        return executeUpdate(deleteUserSQL);
    }

    public List<User> getRead(String WhereAndOreder) throws SQLException {
        List<User> users = new ArrayList<>();
        String readUserSQL = "SELECT * FROM user " + WhereAndOreder;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readUserSQL);
            while (resultSet.next()) {
                User tmpUser = new User(
                        resultSet.getInt("ID"),
                        resultSet.getString("Login"),
                        resultSet.getString("Password"),
                        resultSet.getString("Name"),
                        resultSet.getString("Nick"),
                        resultSet.getInt("Telephone"),
                        resultSet.getInt("Role_ID"));
                users.add(tmpUser);
            }
        }
        return users;
    }


}

