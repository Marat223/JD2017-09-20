package by.it.mustaphin.project.java.controller;

import by.it.mustaphin.project.java.bean.Right;
import by.it.mustaphin.project.java.bean.User;
import by.it.mustaphin.project.java.controller.AbstractAction;
import by.it.mustaphin.project.java.controller.ICommand;
import by.it.mustaphin.project.java.controller.Messages;
import by.it.mustaphin.project.java.dao.DAO;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class CmdSignup extends AbstractAction {

    @Override
    public ICommand execute(HttpServletRequest req) {
        try {
            DAO dao = DAO.getDAO();
            User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), 0);
            dao.user.create(user);
            Map<String, Boolean> rights = new HashMap<String, Boolean>();
            rights.put("admin", false);
            rights.put("user", true);
            rights.put("guest", true);
            Right right = new Right(user.getId_user(), rights);
            req.setAttribute(Messages.MESSAGE, "finish");
            return Actions.LOGIN.command;
        } catch (Exception e) {
            req.getServletContext().log(e.getMessage());
            System.out.println(e.getMessage());
            req.setAttribute(Messages.ERROR, e.getMessage());
        }
        return null;
    }
}
