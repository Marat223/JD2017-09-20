package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.beans.Address;
import by.it.meshchenko.jd03_03.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO extends AbstractDAO implements IDAO<Address> {

    @Override
    public Address read(int id) {
        List<Address> items = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (items.size() > 0) {
            return items.get(0);
        } else
            return null;
    }

    @Override
    public boolean create(Address item) {
        String sql = String.format(
                "INSERT INTO addr_addresses(" +
                        "StreetId,BuildingNumberStr)" +
                        " VALUES(%d,'%s');",
                item.getStreetId(),
                item.getBuildingNumberStr()
        );
        item.setId(executeCreate(sql));
        return (item.getId()>0);
    }

    @Override
    public boolean update(Address item) {
        String sql = String.format(
                "UPDATE addr_addresses SET " +
                        "StreetId = %d, BuildingNumberStr = '%s' WHERE Id = %d",
                item.getStreetId(),
                item.getBuildingNumberStr(),
                item.getId()
        );
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(Address item) {
        String sql = String.format(
                "DELETE FROM crm_users WHERE Id = %d", item.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<Address> getAll(String WhereAndOrder) {
        List<Address> items = new ArrayList<>();
        String sql = "SELECT * FROM crm_users " + WhereAndOrder + " ;";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            Object tempObj;
            while (rs.next()) {
                Address item = new Address();

                item.setId(rs.getInt("Id"));
                item.setStreetId(rs.getInt("StreetId"));
                item.setBuildingNumberStr(rs.getString("BuildingNumberStr"));

                items.add(item);
            }
        } catch (SQLException e) {
            //тут нужно логгирование SQLException(e);
        }
        return items;
    }
}
