package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.beans.Street;
import by.it.meshchenko.jd03_03.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StreetDAO extends AbstractDAO implements IDAO<Street> {

    @Override
    public Street read(int id) {
        List<Street> items = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (items.size() > 0) {
            return items.get(0);
        } else
            return null;
    }

    @Override
    public boolean create(Street item) {
        String sql = String.format(
                "INSERT INTO addr_streets(" +
                        "Name,CityId) " +
                        "VALUES('%s',%d);",
                item.getName(),
                item.getCityId()
        );
        item.setId(executeCreate(sql));
        return (item.getId()>0);
    }

    @Override
    public boolean update(Street item) {
        String sql = String.format(
                "UPDATE addr_streets SET " +
                        "Name = '%s', CityId = %d WHERE Id = %d",
                item.getName(),
                item.getCityId(),
                item.getCityId()
        );
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(Street item) {
        String sql = String.format("DELETE FROM addr_streets WHERE Id = %d", item.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<Street> getAll(String WhereAndOrder) {
        List<Street> items = new ArrayList<>();
        String sql = "SELECT * FROM addr_streets " + WhereAndOrder + " ;";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Street item = new Street();

                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setCityId(rs.getInt("CityId"));

                items.add(item);
            }
        } catch (SQLException e) {
            //тут нужно логгирование SQLException(e);
        }
        return items;
    }
}
