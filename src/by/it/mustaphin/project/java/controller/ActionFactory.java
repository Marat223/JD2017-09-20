package by.it.mustaphin.project.java.controller;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    ICommand getCommand(HttpServletRequest req) {
	try {
	    Actions tmp = Actions.valueOf(req.getParameter("command").toUpperCase());
	    return tmp.command;
	} catch (IllegalArgumentException e) {
	    return Actions.ERROR.command;
	}
    }

}
