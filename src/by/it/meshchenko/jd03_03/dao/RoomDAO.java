package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.beans.Room;
import by.it.meshchenko.jd03_03.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends AbstractDAO implements IDAO<Room> {

    @Override
    public Room read(int id) {
        List<Room> items = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (items.size() > 0) {
            return items.get(0);
        } else
            return null;
    }

    @Override
    public boolean create(Room item) {
        String sql = String.format(
                "INSERT INTO ls_rooms(" +
                        "Name%s,ShoppingCenterId)" +
                        " VALUES('%s'%s,%d);",
                item.getSquare() == null ? "" : ",Square",
                item.getName(),
                item.getSquare() == null ? "" : ",\'" + item.getSquare() + "\'",
                item.getShoppingCenterId()
        );
        item.setId(executeCreate(sql));
        return (item.getId()>0);
    }

    @Override
    public boolean update(Room item) {
        String sql = String.format(
                "UPDATE ls_rooms SET " +
                        "Name = '%s'%s, ShoppingCenterId = %d WHERE Id = %d",
                item.getName(),
                item.getSquare() == null ? "" : ",Square=\'" + item.getSquare() + "\'",
                item.getShoppingCenterId(),
                item.getId()
        );
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(Room item) {
        String sql = String.format(
                "DELETE FROM ls_rooms WHERE Id = %d", item.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<Room> getAll(String WhereAndOrder) {
        List<Room> items = new ArrayList<>();
        String sql = "SELECT * FROM ls_rooms " + WhereAndOrder + " ;";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            Object tempObj;
            while (rs.next()) {
                Room item = new Room();

                item.setId(rs.getInt("Id"));
                item.setName(rs.getString("Name"));
                item.setSquare((tempObj = rs.getObject("Square")) == null
                        ? null : Float.valueOf(tempObj.toString()));
                item.setShoppingCenterId(rs.getInt("ShoppingCenterId"));

                items.add(item);
            }
        } catch (SQLException e) {
            //тут нужно логгирование SQLException(e);
        }
        return items;
    }
}
