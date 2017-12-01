package by.it.ali.project.java.controller;


public class CmdError implements ICommand {
    @Override
    public ICommand execute() {
        return null;
    }

    @Override
    public String getJsp() {
        return "error.jsp";
    }
}
