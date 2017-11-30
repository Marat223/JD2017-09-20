package by.it.govor.bigBossProject.dao;


import by.it.govor.bigBossProject.table.Role;

public class DAO {

    private static DAO dao;

    public UserDAO user;
    public AdDAO ad;
    public AddressDAO address;
    public RoleDAO role;


    private DAO() {
    }

    public static DAO getInstanceUser() {
        if (dao == null) {
            synchronized (DAO.class) {
                if (dao == null) {
                    dao = new DAO();
                    dao.user = new UserDAO();
                }
            }
        }
        return dao;
    }
    public static DAO getInstanceAd() {
        if (dao == null) {
            synchronized (DAO.class) {
                if (dao == null) {
                    dao = new DAO();
                    dao.ad = new AdDAO();
                }
            }
        }
        return dao;
    }

    public static DAO getInstanceAddress() {
        if (dao == null) {
            synchronized (DAO.class) {
                if (dao == null) {
                    dao = new DAO();
                    dao.address = new AddressDAO();
                }
            }
        }
        return dao;
    }

    public static DAO getInstanceRole() {
        if (dao == null) {
            synchronized (DAO.class) {
                if (dao == null) {
                    dao = new DAO();
                    dao.role = new RoleDAO();
                }
            }
        }
        return dao;
    }
}
