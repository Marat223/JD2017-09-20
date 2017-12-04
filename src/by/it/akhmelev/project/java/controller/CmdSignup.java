package by.it.akhmelev.project.java.controller;

import by.it.akhmelev.project.java.bean.User;
import by.it.akhmelev.project.java.dao.DAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


class CmdSignup extends AbstractAction {


    @Override
    public ICommand execute(HttpServletRequest req) {

        if (FormValidator.isPost(req)) {
            User user = new User();
            try {
                user.setLogin(FormValidator.getString(req, "login", Patterns.LOGIN));
                user.setEmail(FormValidator.getString(req, "email", Patterns.EMAIL));
                user.setPassword(FormValidator.getString(req, "password", Patterns.PASSWORD));
                user.setFk_Roles(2);
                DAO dao = DAO.getInstance();
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
