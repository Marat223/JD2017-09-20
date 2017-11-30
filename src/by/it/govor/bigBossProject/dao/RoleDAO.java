package by.it.govor.bigBossProject.dao;


import by.it.govor.bigBossProject.ConnectionCreator;
import by.it.govor.bigBossProject.crud.ICRUD;
import by.it.govor.bigBossProject.table.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends AbstractDAO implements ICRUD<Role> {
    @Override
    public boolean create(Role role) throws SQLException {
        String createRoleSQL = String.format(
                "insert into role(role) values('%s');",
                role.getRole()
        );
        int id=executeCreate(createRoleSQL);
        if (id>0) role.setId(id);
        return (id>0);
    }

    @Override
    public boolean update(Role role) throws SQLException {
        String updateRoleSQL = String.format(
                "UPDATE role SET role = '%s' WHERE role.ID = '%d'",
                role.getRole(),role.getId()
        );
        return executeUpdate(updateRoleSQL);
    }

    @Override
    public boolean delete(Role role) throws SQLException {
        String deleteRoleSQL = String.format("DELETE FROM role WHERE role.ID = '%d'", role.getId());
        return executeUpdate(deleteRoleSQL);
    }

    public List<Role> getRead(String WhereAndOrder) throws SQLException {
        List<Role> roles = new ArrayList<>();
        String readRoleSql = "SELECT * FROM role " + WhereAndOrder;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readRoleSql);
            while (resultSet.next()) {
                Role tmpRole = new Role(
                        resultSet.getInt("ID"),
                        resultSet.getString("role"));
                roles.add(tmpRole);
            }
        }
        return roles;
    }
}
