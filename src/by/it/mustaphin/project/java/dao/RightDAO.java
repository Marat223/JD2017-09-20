package by.it.mustaphin.project.java.dao;

import by.it.mustaphin.project.java.bean.Right;
import by.it.mustaphin.project.java.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RightDAO extends AbstactDAO implements InterfaceDAO<Right> {

    private static RightDAO rightDAO = null;

    public static RightDAO getRightDAO() {
        if (null == rightDAO) {
            synchronized (RightDAO.class) {
                if (null == rightDAO) {
                    rightDAO = new RightDAO();
                }
            }
        }
        return rightDAO;
    }

    //convert to Bean parameter
    private boolean convBean(int par) {
        return par == 1;
    }

    //convert to SQL parameter
    private int convSQL(boolean par) {
        if (par) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean create(Right right) throws SQLException {
        int id_user = right.getId_right();
        int admin = convSQL(right.isAdmin());
        int user = convSQL(right.isUser());
        int guest = convSQL(right.isGuest());
        return (0 < executeCreate("INSERT INTO rights (id_right, admin, user, guest) VALUES (" + id_user + ", " + admin + ", " + user + ", " + guest + ");"));
    }

    @Override
    public boolean update(Right right) throws SQLException {
        int id_right = right.getId_right();
        int admin = convSQL(right.isAdmin());
        int user = convSQL(right.isUser());
        int guest = convSQL(right.isGuest());
        return (1 == executeCreate("UPDATE rights SET admin=" + admin + ", user=" + user + ", guest=" + guest + " WHERE id_right='" + id_right + "';"));
    }

    @Override
    public boolean delete(Right right) throws SQLException {
        return (0 < executeCreate("DELETE FROM rights WHERE id_right='" + right.getId_right() + "';"));
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return (0 < executeCreate("DELETE FROM rights WHERE id_right='" + id + "';"));
    }

    @Override
    public Right read(Right right) throws SQLException {
        try (Connection con = ConnectionCreator.getConnection();
                Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM rights WHERE id_right='" + right.getId_right() + "';");
            if (rs.next()) {
                right = new Right(right.getId_right(), convBean(rs.getInt("admin")), convBean(rs.getInt("user")), convBean(rs.getInt("guest")));
            }
        } catch (SQLException e) {
            throw e;
        }
        return right;
    }

    @Override
    public Right read(int id) throws SQLException {
        Right right = null;
        try (Connection con = ConnectionCreator.getConnection();
                Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM rights WHERE id_right='" + id + "';");
            if (rs.next()) {
                right = new Right(id, convBean(rs.getInt("admin")), convBean(rs.getInt("user")), convBean(rs.getInt("guest")));
            }
        } catch (SQLException e) {
            throw e;
        }
        return right;
    }

    @Override
    public List<Right> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<Right> getAll(String condition) throws SQLException {
        List<Right> pack = new ArrayList<>();
        try (Connection con = ConnectionCreator.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM rights" + condition + ";");
            while (rs.next()) {
                pack.add(new Right(rs.getInt("id_right"), convBean(rs.getInt("admin")), convBean(rs.getInt("user")), convBean(rs.getInt("guest"))));
            }
        } catch (SQLException e) {
            throw e;
        }
        return pack;
    }
}
