package by.it.akhmelev.project.java.controller;

abstract class AbstractAction implements ICommand {

    @Override
    public String getJsp() {
        return "/" + this.toString().
                toLowerCase() +
                ".jsp";
    }

    @Override
    public String toString() {
        return this.getClass().
               getSimpleName().
               replace("Cmd", "");
    }


}
