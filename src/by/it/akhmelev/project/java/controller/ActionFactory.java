package by.it.akhmelev.project.java.controller;

import javax.servlet.http.HttpServletRequest;

class ActionFactory {
    ICommand getCommand(HttpServletRequest req) {
        String strCommand = req.getParameter("command").toUpperCase();
        try {
            Actions tmp = Actions.valueOf(strCommand);
            return tmp.command;
        } catch (IllegalArgumentException e) {
            return Actions.ERROR.command;
        }
    }
}
