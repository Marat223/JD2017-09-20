package by.it.mustaphin.project.java.controller;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    
    ICommand getCommand(HttpServletRequest req) {
        System.out.println("req " + req.getParameter("command").toUpperCase());
        try {
            return Actions.valueOf(req.getParameter("command").toUpperCase()).command;
        } catch (IllegalArgumentException e) {
            return Actions.ERROR.command;
        }
    }
    
}
