package by.it.meshchenko.jd03_02.crud;

import by.it.meshchenko.jd03_02.ConnectionCreator;
import by.it.meshchenko.jd03_02.beans.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AddressCRUD {

    public Address create(Address item) throws SQLException {
        item.setId(0);
        String createItemSQL = String.format(
                "INSERT INTO addr_addresses(" +
                        "StreetId,BuildingNumberStr)" +
                        " VALUES(%d,'%s');",
                item.getStreetId(),
                item.getBuildingNumberStr()
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

    public Address read(int id) throws SQLException {
        Address itemResult = null;
        String readItemSQL = "SELECT * FROM addr_addresses WHERE ID=" + id;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readItemSQL);
            if (resultSet.next()) {
                itemResult = new Address(
                        resultSet.getInt("Id"),
                        resultSet.getInt("StreetId"),
                        resultSet.getString("BuildingNumberStr"));
            }
        }
        return itemResult;
    }

    public Address update(Address item) throws SQLException {
        Address itemResult = null;
        //String temp = "";
        String updateItemSQL = String.format(
                "UPDATE addr_addresses SET " +
                        "StreetId = %d, BuildingNumberStr = '%s' WHERE Id = %d",
                item.getStreetId(),
                item.getBuildingNumberStr(),
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

    public boolean delete(Address item) throws SQLException {
        String deleteItemSQL = String.format("DELETE FROM addr_addresses WHERE Id = %d", item.getId());
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            return (statement.executeUpdate(deleteItemSQL) == 1);
        }
    }
}
