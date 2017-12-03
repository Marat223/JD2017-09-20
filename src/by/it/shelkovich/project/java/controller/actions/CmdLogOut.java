package by.it.shelkovich.project.java.controller.actions;

public class CmdLogOut extends AbstractAction {
    @Override
    public ICommand execute() {
        return new CmdMain();
    }
}
