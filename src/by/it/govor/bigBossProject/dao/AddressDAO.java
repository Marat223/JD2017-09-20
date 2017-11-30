package by.it.govor.bigBossProject.dao;

import by.it.govor.bigBossProject.ConnectionCreator;
import by.it.govor.bigBossProject.crud.ICRUD;
import by.it.govor.bigBossProject.table.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AddressDAO extends AbstractDAO implements ICRUD<Address> {
    @Override
    public boolean create(Address address) throws SQLException {
        String createAdSQL = String.format(
                "insert into address(Country,City,user_ID) values('%s','%s','%d');",
                address.getCountry(), address.getCity(), address.getUser_ID()
        );
        int id=executeCreate(createAdSQL);
        if (id>0) address.setId(id);
        return (id>0);
    }

    @Override
    public boolean update(Address address ) throws SQLException {
        String updateAddressSQL = String.format(
                "UPDATE address SET country = '%s', city = '%s',user_ID= '%d' WHERE address.ID = %d",
                address.getCountry(), address.getCity(),address.getUser_ID(),address.getId()
        );
        return executeUpdate(updateAddressSQL);
    }

    @Override
    public boolean delete(Address address) throws SQLException {
        String deleteAdSQL = String.format("DELETE FROM address WHERE address.ID = '%d'", address.getId());
        return executeUpdate(deleteAdSQL);
    }
    public List<Address> getRead(String WhereAndOrder) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        String readAddressSql = "SELECT * FROM address " + WhereAndOrder;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readAddressSql);
            while (resultSet.next()) {
                Address tmpAd = new Address(
                        resultSet.getInt("ID"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getInt("user_ID"));
                addresses.add(tmpAd);
            }
        }
        return addresses;
    }
}
