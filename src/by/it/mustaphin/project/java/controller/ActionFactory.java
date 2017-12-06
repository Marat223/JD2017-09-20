package by.it.mustaphin.project.java.controller;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    ICommand getCommand(HttpServletRequest req) {
        String strCommand = req.getParameter("command").toUpperCase();
        try {
            Actions tmp = Actions.valueOf(strCommand);
            return tmp.command;
        } catch (IllegalArgumentException e) {
            return Actions.ERROR.command;
        }
    }

//    ICommand getCommand(HttpServletRequest req) {
//        if (null == req.getParameter("command")) {
//            return Actions.SIGNUP.command;
//        }
//        try {
//            return Actions.valueOf(req.getParameter("command").toUpperCase()).command;
//        } catch (IllegalArgumentException e) {
//            return Actions.ERROR.command;
//        }
//    }
}
