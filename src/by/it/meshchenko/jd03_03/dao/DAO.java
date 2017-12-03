package by.it.meshchenko.jd03_03.dao;

import by.it.meshchenko.jd03_03.beans.*;

public class DAO {

    private static DAO dao;

    public AddressDAO address;
    public CityDAO city;
    public CountryDAO country;
    public LeaseRoomDAO leaseRoom;
    public RoomDAO room;
    public ShoppingCenterDAO shoppingCenter;
    public StreetDAO street;
    public UserDAO user;
    public UniversalDAO<Role> role;

    private DAO() {
    }

    public static DAO getInstance() {
        if (dao == null) {
            synchronized (DAO.class) {
                if (dao == null) {
                    dao = new DAO();
                    dao.address = new AddressDAO();
                    dao.city = new CityDAO();
                    dao.country = new CountryDAO();
                    dao.leaseRoom = new LeaseRoomDAO();
                    dao.room = new RoomDAO();
                    dao.shoppingCenter = new ShoppingCenterDAO();
                    dao.street = new StreetDAO();
                    dao.user = new UserDAO();
                    dao.role = new UniversalDAO<>(new Role(), "crm_roles");
                }
            }
        }
        return dao;
    }
}
