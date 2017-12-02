package by.it.meshchenko.jd03_02.crud;

import by.it.meshchenko.jd03_02.ConnectionCreator;
import by.it.meshchenko.jd03_02.beans.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class CountryCRUD {

    public Country create(Country item) throws SQLException {
        item.setId(0);
        String createItemSQL = String.format(
                "INSERT INTO addr_countries(" +
                        "Name) " +
                        "VALUES('%s');",
                item.getName()
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

    public Country read(int id) throws SQLException {
        Country itemResult = null;
        String readItemSQL = "SELECT * FROM addr_countries WHERE ID=" + id;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readItemSQL);
            if (resultSet.next()) {
                itemResult = new Country(
                        resultSet.getInt("Id"),
                        resultSet.getString("Name"));
            }
        }
        return itemResult;
    }

    public Country update(Country item) throws SQLException {
        Country itemResult = null;
        String updateItemSQL = String.format(
                "UPDATE addr_countries SET " +
                        "Name = '%s' WHERE Id = %d",
                item.getName(),
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

    public boolean delete(Country item) throws SQLException {
        String deleteItemSQL = String.format("DELETE FROM addr_countries WHERE Id = %d", item.getId());
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            return (statement.executeUpdate(deleteItemSQL) == 1);
        }
    }
}
