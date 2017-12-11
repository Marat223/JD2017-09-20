package by.it.mustaphin.project.java.controller;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    ICommand getCommand(HttpServletRequest req) {
        try {
            return Actions.valueOf(req.getParameter("command").toUpperCase()).command;
        } catch (IllegalArgumentException e) {
            return Actions.ERROR.command;
        }
    }

}
