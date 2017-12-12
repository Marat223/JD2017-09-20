package by.it.mustaphin.project.java.controller;

import by.it.mustaphin.project.java.dao.DAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    @Override
    public void init() throws ServletException {
	super.init();
	try {
	    getServletContext().setAttribute("rights", DAO.getDAO().right.getAll());
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	ICommand cmd = new ActionFactory().getCommand(req);
	ICommand nextAction = cmd.execute(req);
	if (null == nextAction) {
	    req.getRequestDispatcher(cmd.getJsp()).forward(req, resp);
	} else {
	    resp.sendRedirect("do?command=" + nextAction);
	}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	ICommand cmd = new ActionFactory().getCommand(req);
	ICommand nextAction = cmd.execute(req);
	if (null == nextAction) {
	    req.getRequestDispatcher(cmd.getJsp()).forward(req, resp);
	} else {
	    resp.sendRedirect("do?command=" + nextAction);
	}
    }

}
