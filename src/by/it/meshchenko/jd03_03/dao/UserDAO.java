package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.beans.*;
import by.it.meshchenko.jd03_03.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO implements IDAO<User> {

    @Override
    public User read(int id) {
        List<User> items = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (items.size() > 0) {
            return items.get(0);
        } else
            return null;
    }

    @Override
    public boolean create(User item) {
        String sql = String.format(
                "INSERT INTO crm_users(" +
                        "Name,Password,%sEmail,EmailConfirmed%s)" +
                        " VALUES('%s','%s',%s'%s',%d%s);",
                item.getPhoneNumber() == null ? "" : "PhoneNumber,",
                item.getRoleId() == null ? "" : ",RoleId",
                item.getName(), //PhoneNumber
                item.getPassword(),
                item.getPhoneNumber() == null ? "" : "\'" + item.getPhoneNumber() + "\',",
                item.getEmail(),
                item.isEmailConfirmed() ? 1 : 0,
                item.getRoleId() == null ? "" : "," + item.getRoleId()
        );
        item.setId(executeCreate(sql));
        return (item.getId()>0);
    }

    @Override
    public boolean update(User item) {
        String sql = String.format(
                "UPDATE crm_users SET " +
                        "Name = '%s', Password = '%s',%s Email = '%s', " +
                        "EmailConfirmed = %d%s WHERE Id = %d",
                item.getName(),
                item.getPassword(),
                item.getPhoneNumber() == null ? "" : "PhoneNumber=\'" + item.getPhoneNumber() + "\',",
                item.getEmail(),
                item.isEmailConfirmed() ? 1 : 0,
                item.getRoleId() == null ? "" : ",RoleId=" + item.getRoleId().toString(),
                item.getId()
        );
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(User item) {
        String sql = String.format(
                "DELETE FROM crm_users WHERE Id = %d", item.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<User> getAll(String WhereAndOrder) {
        List<User> items = new ArrayList<>();
        String sql = "SELECT * FROM crm_users " + WhereAndOrder + " ;";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            Object tempObj;
            while (rs.next()) {
                User item = new User();

                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setPassword(rs.getString("Password"));
                item.setPhoneNumber((tempObj = rs.getObject("PhoneNumber")) == null
                        ? null : tempObj.toString());
                item.setEmail(rs.getString("Email"));
                item.setEmailConfirmed(rs.getBoolean("EmailConfirmed"));
                item.setRoleId((tempObj = rs.getObject("RoleId")) == null
                        ? null : Integer.valueOf(tempObj.toString()));

                items.add(item);
            }
        } catch (SQLException e) {
            //тут нужно логгирование SQLException(e);
        }
        return items;
    }
}
