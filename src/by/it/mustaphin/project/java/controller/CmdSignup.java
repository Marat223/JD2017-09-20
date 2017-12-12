package by.it.mustaphin.project.java.controller;

import by.it.mustaphin.project.java.bean.User;
import by.it.mustaphin.project.java.dao.DAO;
import javax.servlet.http.HttpServletRequest;

public class CmdSignup extends AbstractAction {

    @Override
    public ICommand execute(HttpServletRequest req) {
	if (req.getMethod().equalsIgnoreCase("post")) {
	    try {
		DAO dao = DAO.getDAO();
		if (dao.user.getAll(" WHERE id_user='" + req.getParameter("login") + "'").size() > 0) {
		    req.setAttribute(Messages.ERROR, "That user already exists");
		    return null;
		}
		User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), 1, 2);
		dao.user.create(user);
		req.setAttribute(Messages.MESSAGE, "finish");
		return Actions.LOGIN.command;
	    } catch (Exception e) {
		req.getServletContext().log(e.getMessage());
		System.out.println(e.getMessage());
		req.setAttribute(Messages.ERROR, e.getMessage());
	    }
	}
	return null;
    }
}
