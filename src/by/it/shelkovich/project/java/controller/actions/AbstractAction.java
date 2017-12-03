package by.it.shelkovich.project.java.controller.actions;

public abstract class AbstractAction implements ICommand {

    @Override
    public String getJSP() {
        return "/"+this.getClass().getSimpleName().replace("Cmd", "").toLowerCase()+".jsp";
    }
}
