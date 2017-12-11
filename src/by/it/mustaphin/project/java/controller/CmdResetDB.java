package by.it.mustaphin.project.java.controller;

import by.it.mustaphin.project.java.dao.InitDB;
import javax.servlet.http.HttpServletRequest;

public class CmdResetDB extends AbstractAction {

    @Override
    public ICommand execute(HttpServletRequest req) {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            InitDB.main(null);
            return Actions.SIGNUP.command;
        }
        return null;
    }
}
