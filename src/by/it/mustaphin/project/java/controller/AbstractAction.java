package controller;

public abstract class AbstractAction implements ICommand {

    @Override
    public String getJsp() {
        return "/" + this.getClass().getSimpleName().replace("Cmd", "").toLowerCase() + ".jsp";
    }
}
