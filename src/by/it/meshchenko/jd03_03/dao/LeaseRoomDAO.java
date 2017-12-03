package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.beans.LeaseRoom;
import by.it.meshchenko.jd03_03.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeaseRoomDAO extends AbstractDAO implements IDAO<LeaseRoom> {

    @Override
    public LeaseRoom read(int id) {
        List<LeaseRoom> items = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (items.size() > 0) {
            return items.get(0);
        } else
            return null;
    }

    @Override
    public boolean create(LeaseRoom item) {
        String sql = String.format(
                "INSERT INTO ls_leaserooms(" +
                        "%s%s%sRoomId,UserId)" +
                        " VALUES(%s%s%s%d,%d);",
                item.getPrice() == null ? "" : "Price,",
                item.getDateStartLease() == null ? "" : "DateStartLease,",
                item.getDateStopLease() == null ? "" : "DateStopLease,",
                item.getPrice() == null ? "" : item.getPrice().toString() + ",",
                item.getDateStartLease() == null ? "" :
                        "\'" + item.getDateStartLease() + "\',",
                item.getDateStopLease() == null ? "" :
                        "\'" + item.getDateStopLease() + "\',",
                item.getRoomId(),
                item.getUserId()
        );
        item.setId(executeCreate(sql));
        return (item.getId()>0);
    }

    @Override
    public boolean update(LeaseRoom item) {
        String sql = String.format(
                "UPDATE ls_leaserooms SET " +
                        "%s%s%sRoomId = %d, UserId = %d WHERE Id = %d",
                item.getPrice() == null ? "" : "Price=\'" + item.getPrice().toString() + "\',",
                item.getDateStartLease() == null ? "" : "DateStartLease=\'"
                        + item.getDateStartLease().toString() + "\',",
                item.getDateStartLease() == null ? "" : "DateStopLease=\'"
                        + item.getDateStopLease().toString() + "\',",
                item.getRoomId(),
                item.getUserId(),
                item.getId()
        );
        return executeUpdate(sql);
    }

    @Override
    public boolean delete(LeaseRoom item) {
        String sql = String.format(
                "DELETE FROM ls_leaserooms WHERE Id = %d", item.getId());
        return executeUpdate(sql);
    }

    @Override
    public List<LeaseRoom> getAll(String WhereAndOrder) {
        List<LeaseRoom> items = new ArrayList<>();
        String sql = "SELECT * FROM ls_leaserooms " + WhereAndOrder + " ;";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            Object tempObj;
            while (rs.next()) {
                LeaseRoom item = new LeaseRoom();

                item.setId(rs.getInt("Id"));
                item.setPrice((tempObj = rs.getObject("Price")) == null
                        ? null : Float.valueOf(tempObj.toString()));
                item.setDateStartLease(rs.getDate("DateStartLease"));
                item.setDateStopLease(rs.getDate("DateStopLease"));
                item.setRoomId(rs.getInt("RoomId"));
                item.setUserId(rs.getInt("UserId"));

                items.add(item);
            }
        } catch (SQLException e) {
            //тут нужно логгирование SQLException(e);
        }
        return items;
    }
}
