package by.it.mustaphin.project.java.dao;

import by.it.mustaphin.project.java.connection.ConnectionCreator;
import by.it.mustaphin.project.java.bean.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstactDAO implements InterfaceDAO<User> {

    private static UserDAO userDAO = null;

    public static UserDAO getUserDAO() {
	if (null == userDAO) {
	    synchronized (UserDAO.class) {
		if (null == userDAO) {
		    userDAO = new UserDAO();
		}
	    }
	}
	return userDAO;
    }

    @Override
    public boolean create(User user) throws SQLException {
	String name = user.getName();
	String login = user.getLogin();
	String password = user.getPassword();
	int fk_question = user.getFk_question();
	int fk_right = user.getFk_right();
	int id = executeCreate("INSERT INTO users (name, login, password, fk_question, fk_right) VALUES ('" + name + "', '" + login + "', '" + password + "'," + fk_question + ",'" + fk_right + "');");
	if (0 < id) {
	    user.setId_user(id);
	}
	return (0 < id);
    }

    @Override
    public boolean update(User user) throws SQLException {
	return (1 == executeCreate("UPDATE users SET login='" + user.getName() + "', password='" + user.getLogin() + "' , name='" + user.getPassword() + "', fk_question='" + user.getFk_question() + "' , fk_right='" + user.getFk_right() + "' WHERE id_user='" + user.getId_user() + "';"));
    }

    @Override
    public boolean delete(User user) throws SQLException {
	return (0 < executeCreate("DELETE FROM users WHERE id_user='" + user.getId_user() + "';"));
    }

    @Override
    public boolean delete(int id) throws SQLException {
	return (0 < executeCreate("DELETE FROM users WHERE id_user='" + id + "';"));
    }

    @Override
    public User read(User user) throws SQLException {
	try (Connection con = ConnectionCreator.getConnection();
		Statement st = con.createStatement()) {
	    ResultSet rs = st.executeQuery("SELECT * FROM users WHERE id_user='" + user.getId_user() + "';");
	    if (rs.next()) {
		user = new User(
			rs.getInt("id_user"),
			rs.getString("name"),
			rs.getString("login"),
			rs.getString("password"),
			rs.getInt("fk_question"),
			rs.getInt("fk_right"));
	    }
	} catch (SQLException e) {
	    throw e;
	}
	return user;
    }

    @Override
    public User read(int id) throws SQLException {
	User user = null;
	try (Connection con = ConnectionCreator.getConnection();
		Statement st = con.createStatement()) {
	    ResultSet rs = st.executeQuery("SELECT * FROM users WHERE id_user='" + id + "';");
	    if (rs.next()) {
		user = new User(
			rs.getInt("id_user"),
			rs.getString("name"),
			rs.getString("login"),
			rs.getString("password"),
			rs.getInt("fk_question"),
			rs.getInt("fk_right"));
	    }
	} catch (SQLException e) {
	    throw e;
	}
	return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
	return getAll("");
    }

    @Override
    public List<User> getAll(String condition) throws SQLException {
	List<User> users = new ArrayList<>();
	try (Connection con = ConnectionCreator.getConnection(); Statement st = con.createStatement()) {
	    ResultSet rs = st.executeQuery("SELECT * FROM users" + condition + ";");
	    while (rs.next()) {
		User user = new User(
			rs.getInt("id_user"),
			rs.getString("name"),
			rs.getString("login"),
			rs.getString("password"),
			rs.getInt("fk_question"),
			rs.getInt("fk_right"));
		users.add(user);
	    }
	} catch (SQLException e) {
	    throw e;
	}
	return users;
    }
}
