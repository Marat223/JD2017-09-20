package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.beans.ShoppingCenter;
import by.it.meshchenko.jd03_03.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCenterDAO extends AbstractDAO implements IDAO<ShoppingCenter> {

    @Override
    public ShoppingCenter read(int id) {
        List<ShoppingCenter> items = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (items.size() > 0) {
            return items.get(0);
        } else
            return null;
    }

    @Override
    public boolean create(ShoppingCenter item) {
        String sql = String.format(
                "INSERT INTO ls_shoppingcenters " +
                        "(Name,AddressId%s%s%s)" +
                        " VALUES('%s',%d%s%s%s);",
                item.getLat() == null ? "" : ",Lat",
                item.getLng() == null ? "" : ",Lng",
                item.getDescription() == null ? "" : ",Description",
                item.getName(),
                item.getAddressId(),
                item.getLat() == null ? "" : ",\'" + item.getLat().toString() + "\'",
                item.getLng() == null ? "" : ",\'" + item.getLng().toString() + "\'",
                item.getDescription() == null ? "" : ",\'" + item.getDescription() + "\'"
        );
        item.setId(executeCreate(sql));
        return (item.getId()>0);
    }

    @Override
    public boolean update(ShoppingCenter item) {
        String sql = String.format(
                "UPDATE ls_shoppingcenters SET " +
                        "Name = '%s', AddressId = %d%s%s%s WHERE Id = %d",
                item.getName(),
                item.getAddressId(),
                item.getLat() == null ? "" : ",Lat=\'" + item.getLat() + "\'",
                item.getLng() == null ? "" : ",Lng=\'" + item.getLng() + "\'",
                item.getDescription() == null ? "" : ",Description=\'" + item.getDescription() + "\'",
                item.getId()
        );
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(ShoppingCenter item) {
        String sql = String.format(
                "DELETE FROM ls_shoppingcenters WHERE Id = %d", item.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<ShoppingCenter> getAll(String WhereAndOrder) {
        List<ShoppingCenter> items = new ArrayList<>();
        String sql = "SELECT * FROM ls_shoppingcenters " + WhereAndOrder + " ;";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            Object tempObj;
            while (rs.next()) {
                ShoppingCenter item = new ShoppingCenter();

                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setAddressId(rs.getInt("AddressId"));
                item.setLat((tempObj = rs.getObject("Lat")) == null
                        ? null : Float.valueOf(tempObj.toString()));
                item.setLng((tempObj = rs.getObject("Lng")) == null
                        ? null : Float.valueOf(tempObj.toString()));
                item.setDescription((tempObj = rs.getObject("Description")) == null
                        ? null : tempObj.toString());

                items.add(item);
            }
        } catch (SQLException e) {
            //тут нужно логгирование SQLException(e);
        }
        return items;
    }
}
