package by.it.akhmelev.project.java.dao;

import by.it.akhmelev.project.java.bean.User;
import by.it.akhmelev.project.java.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO implements InterfaceDAO<User>{
    @Override
    public boolean create(User user) throws SQLException {
        String createUserSQL = String.format(
                "insert into users(Login,Password,Email,FK_roles) values('%s','%s','%s',%d);",
                user.getLogin(), user.getPassword(), user.getEmail(), user.getFk_Roles()
        );
        int id=executeCreate(createUserSQL);
        if (id>0) user.setId(id);
        return (id>0);
    }

    @Override
    public boolean update(User user) throws SQLException {
        String updateUserSQL = String.format(
                "UPDATE users SET Login = '%s', Password = '%s', Email = '%s', FK_roles=%d WHERE users.ID = %d",
                user.getLogin(), user.getPassword(), user.getEmail(), user.getFk_Roles(), user.getId()
        );
        return executeUpdate(updateUserSQL);
    }

    @Override
    public boolean delete(User user) throws SQLException {
        String deleteUserSQL = String.format("DELETE FROM users WHERE users.ID = %d", user.getId());
        return executeUpdate(deleteUserSQL);
    }

    @Override
    public User read(User user) throws SQLException {
        return getAll("where id="+user.getId()).get(0);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<User> getAll(String WhereAndOreder) throws SQLException {
        List<User> users = new ArrayList<>();
        String readUserSQL = "SELECT * FROM users " + WhereAndOreder;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readUserSQL);
            while (resultSet.next()) {
                User tmpUser = new User(
                        resultSet.getInt("ID"),
                        resultSet.getString("Login"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getInt("FK_roles"));
                users.add(tmpUser);
            }
        }
        return users;
    }
}
