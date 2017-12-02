package by.it.meshchenko.jd03_02.crud;

import by.it.meshchenko.jd03_02.ConnectionCreator;
import by.it.meshchenko.jd03_02.beans.LeaseRoom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class LeaseRoomCRUD {

    public LeaseRoom create(LeaseRoom item) throws SQLException {
        item.setId(0);
        String createItemSQL = String.format(
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
        System.out.println(createItemSQL);
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            //выполняем добавление в базу, должна быть добавлена одна запись. Проверим это.
            //create(insert) update delete - это executeUpdate, а select это executeQuery
            if (1 == statement.executeUpdate(createItemSQL, RETURN_GENERATED_KEYS)) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    item.setId(id);
                }
            }
        }
        return item;
    }

    public LeaseRoom read(int id) throws SQLException {
        LeaseRoom itemResult = null;
        Object tempObj;
        String readItemSQL = "SELECT * FROM ls_leaserooms WHERE ID=" + id;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readItemSQL);
            if (resultSet.next()) {
                itemResult = new LeaseRoom(
                        resultSet.getInt("Id"),
                        (tempObj = resultSet.getObject("Price")) == null
                                ? null : Float.valueOf(tempObj.toString()),
                        resultSet.getDate("DateStartLease"),
                        resultSet.getDate("DateStopLease"),
                        resultSet.getInt("RoomId"),
                        resultSet.getInt("UserId"));
            }
        }
        return itemResult;
    }

    public LeaseRoom update(LeaseRoom item) throws SQLException {
        LeaseRoom itemResult = null;
        //String temp = "";
        String updateItemSQL = String.format(
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
        System.out.println(updateItemSQL);
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.executeUpdate(updateItemSQL) == 1)
                itemResult = item;
        }
        return itemResult;
    }

    public boolean delete(LeaseRoom item) throws SQLException {
        String deleteItemSQL = String.format("DELETE FROM ls_leaserooms WHERE Id = %d", item.getId());
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            return (statement.executeUpdate(deleteItemSQL) == 1);
        }
    }
}
