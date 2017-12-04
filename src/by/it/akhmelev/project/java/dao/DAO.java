package by.it.akhmelev.project.java.dao;

import by.it.akhmelev.project.java.bean.Ad;
import by.it.akhmelev.project.java.bean.Role;

public class DAO {

    private static DAO dao;

    public UniversalDAO<Role> role;
    public UserDAO user;
    public UniversalDAO<Ad> ad;

    private DAO() {
    }

    public static DAO getInstance() {
        if (dao == null) {
            synchronized (DAO.class) {
                if (dao == null) {
                    dao = new DAO();
                    dao.role = new UniversalDAO<>(new Role(), "roles");
                    dao.user = new UserDAO();
                    dao.ad = new UniversalDAO<>(new Ad(), "ads");
                }
            }
        }
        return dao;
    }
}
