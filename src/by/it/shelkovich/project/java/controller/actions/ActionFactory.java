package by.it.shelkovich.project.java.controller.actions;

import javax.servlet.http.HttpServletRequest;

public enum ActionFactory {
    INSTANCE;

    public ICommand getCommand(HttpServletRequest req) {
        String commandValue = req.getParameter("command");
        if(commandValue==null) commandValue = "main";
        commandValue = commandValue.toUpperCase();
        try {
            return Actions.valueOf(commandValue).command;
        } catch (IllegalArgumentException e) {
            return Actions.ERROR.command;
        }
    }
}
