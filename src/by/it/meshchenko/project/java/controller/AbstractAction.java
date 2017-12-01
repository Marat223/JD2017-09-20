package by.it.meshchenko.project.java.controller;

public abstract class AbstractAction implements ICommand {

    @Override
    public abstract ICommand execute();

    @Override
    public String getJsp() {
        String cmdName = this.getClass().getSimpleName();
        cmdName  = cmdName.replace("Cmd", "").toLowerCase();
        return "/" + cmdName + ".jsp";
    }
}
