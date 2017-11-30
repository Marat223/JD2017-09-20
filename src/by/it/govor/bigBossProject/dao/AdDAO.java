package by.it.govor.bigBossProject.dao;


import by.it.govor.bigBossProject.ConnectionCreator;
import by.it.govor.bigBossProject.crud.ICRUD;
import by.it.govor.bigBossProject.table.Ad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdDAO extends AbstractDAO implements ICRUD<Ad> {
    @Override
    public boolean create(Ad ad) throws SQLException {
        String createAdSQL = String.format(
                "insert into ad(processor,video_card,motherboard,ram,power_supply,HDD,user_ID) values('%s','%s','%s','%s','%s','%s','%d');",
                ad.getProcessor(), ad.getVideo_card(), ad.getMotherboard(), ad.getRam(), ad.getPower_supply(), ad.getHDD(),ad.getUser_ID()
        );
        int id=executeCreate(createAdSQL);
        if (id>0) ad.setId(id);
        return (id>0);
    }

    @Override
    public boolean update(Ad ad) throws SQLException {
        String updateAdSQL = String.format(
                "UPDATE ad SET processor = '%s', video_card = '%s', motherboard = '%s', ram = '%s', power_supply = '%s',HDD='%s',user_ID= '%d' WHERE ad.ID = '%d'",
                ad.getProcessor(), ad.getVideo_card(), ad.getMotherboard(), ad.getRam(), ad.getPower_supply(), ad.getHDD(),ad.getUser_ID(),ad.getId()
        );
        return executeUpdate(updateAdSQL);
    }

    @Override
    public boolean delete(Ad ad) throws SQLException {
        String deleteAdSQL = String.format("DELETE FROM ad WHERE ad.ID = '%d'", ad.getId());
        return executeUpdate(deleteAdSQL);
    }

    public List<Ad> getRead(String WhereAndOrder) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        String readAdSql = "SELECT * FROM ad " + WhereAndOrder;
        try (
                Connection connection = ConnectionCreator.getConnection();
                Statement statement = connection.createStatement()
        ) {
            final ResultSet resultSet = statement.executeQuery(readAdSql);
            while (resultSet.next()) {
                Ad tmpAd = new Ad(
                        resultSet.getInt("ID"),
                        resultSet.getString("processor"),
                        resultSet.getString("video_card"),
                        resultSet.getString("motherboard"),
                        resultSet.getString("ram"),
                        resultSet.getString("power_supply"),
                        resultSet.getString("HDD"),
                        resultSet.getInt("user_ID"));
                ads.add(tmpAd);
            }
        }
        return ads;
    }
}
