package by.it.meshchenko.jd03_02.crud;

import by.it.meshchenko.jd03_02.beans.*;
import by.it.meshchenko.jd03_02.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class ShoppingCenterCRUD {

    public ShoppingCenter create(ShoppingCenter item) throws SQLException {
        item.setId(0);
        String createItemSQL = String.format(
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

    public ShoppingCenter read(int id) throws SQLException {
        ShoppingCenter itemResult = null;
        Object tempObj;
        String readItemSQL = "SELECT * FROM ls_shoppingcenters WHERE ID=" + id;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readItemSQL);
            if (resultSet.next()) {
                itemResult = new ShoppingCenter(
                        resultSet.getInt("Id"),
                        resultSet.getString("Name"),
                        resultSet.getInt("AddressId"),
                        (tempObj = resultSet.getObject("Lat")) == null
                                ? null : Float.valueOf(tempObj.toString()),
                        (tempObj = resultSet.getObject("Lng")) == null
                                ? null : Float.valueOf(tempObj.toString()),
                        (tempObj = resultSet.getObject("Description")) == null
                                ? null : tempObj.toString());
            }
        }
        return itemResult;
    }

    public ShoppingCenter update(ShoppingCenter item) throws SQLException {
        ShoppingCenter itemResult = null;
        //String temp = "";
        String updateItemSQL = String.format(
                "UPDATE ls_shoppingcenters SET " +
                        "Name = '%s', AddressId = %d%s%s%s WHERE Id = %d",
                item.getName(),
                item.getAddressId(),
                item.getLat() == null ? "" : ",Lat=\'" + item.getLat() + "\'",
                item.getLng() == null ? "" : ",Lng=\'" + item.getLng() + "\'",
                item.getDescription() == null ? "" : ",Description=\'" + item.getDescription() + "\'",
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

    public boolean delete(ShoppingCenter item) throws SQLException {
        String deleteItemSQL = String.format("DELETE FROM ls_shoppingcenters WHERE Id = %d"
                , item.getId());
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            return (statement.executeUpdate(deleteItemSQL) == 1);
        }
    }
}
