package by.it.meshchenko.jd03_02.crud;

import by.it.meshchenko.jd03_02.ConnectionCreator;
import by.it.meshchenko.jd03_02.beans.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class RoomCRUD {

    public Room create(Room item) throws SQLException {
        item.setId(0);
        String createItemSQL = String.format(
                "INSERT INTO ls_rooms(" +
                        "Name%s,ShoppingCenterId)" +
                        " VALUES('%s'%s,%d);",
                item.getSquare() == null ? "" : ",Square",
                item.getName(),
                item.getSquare() == null ? "" : ",\'" + item.getSquare() + "\'",
                item.getShoppingCenterId()
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

    public Room read(int id) throws SQLException {
        Room itemResult = null;
        Object tempObj;
        String readItemSQL = "SELECT * FROM ls_rooms WHERE ID=" + id;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readItemSQL);
            if (resultSet.next()) {
                itemResult = new Room(
                        resultSet.getInt("Id"),
                        resultSet.getString("Name"),
                        (tempObj = resultSet.getObject("Square")) == null
                                ? null : Float.valueOf(tempObj.toString()),
                        resultSet.getInt("ShoppingCenterId"));
            }
        }
        return itemResult;
    }

    public Room update(Room item) throws SQLException {
        Room itemResult = null;
        String updateItemSQL = String.format(
                "UPDATE ls_rooms SET " +
                        "Name = '%s'%s, ShoppingCenterId = %d WHERE Id = %d",
                item.getName(),
                item.getSquare() == null ? "" : ",Square=\'" + item.getSquare() + "\'",
                item.getShoppingCenterId(),
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

    public boolean delete(Room item) throws SQLException {
        String deleteItemSQL = String.format("DELETE FROM ls_rooms WHERE Id = %d", item.getId());
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            return (statement.executeUpdate(deleteItemSQL) == 1);
        }
    }
}
