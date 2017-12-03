package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.beans.Country;
import by.it.meshchenko.jd03_03.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends AbstractDAO implements IDAO<Country> {

    @Override
    public Country read(int id) {
        List<Country> items = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (items.size() > 0) {
            return items.get(0);
        } else
            return null;
    }

    @Override
    public boolean create(Country item) {
        String sql = String.format(
                "INSERT INTO addr_countries(" +
                        "Name) " +
                        "VALUES('%s');",
                item.getName()
        );
        item.setId(executeCreate(sql));
        return (item.getId()>0);
    }

    @Override
    public boolean update(Country item) {
        String sql = String.format(
                "UPDATE addr_countries SET " +
                        "Name = '%s' WHERE Id = %d",
                item.getName(),
                item.getId()
        );
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(Country item) {
        String sql = String.format(
                "DELETE FROM addr_countries WHERE Id = %d", item.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<Country> getAll(String WhereAndOrder) {
        List<Country> items = new ArrayList<>();
        String sql = "SELECT * FROM addr_countries " + WhereAndOrder + " ;";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Country item = new Country();

                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));

                items.add(item);
            }
        } catch (SQLException e) {
            //тут нужно логгирование SQLException(e);
        }
        return items;
    }
}
