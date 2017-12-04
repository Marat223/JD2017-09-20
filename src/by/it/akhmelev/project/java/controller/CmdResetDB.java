package by.it.akhmelev.project.java.controller;


import by.it.akhmelev.project.java.dao.Init_akhmelev;

import javax.servlet.http.HttpServletRequest;

class CmdResetDB extends AbstractAction {
    @Override
    public ICommand execute(HttpServletRequest req) {
        if (FormValidator.isPost(req)){
            Init_akhmelev.main(null);
            return Actions.SIGNUP.command;
        }
        return null;
    }
}
