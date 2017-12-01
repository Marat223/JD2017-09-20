package by.it.meshchenko.project.java.controller;

public enum Actions {

    LOGIN{
        {
            this.command = new CmdLogin();
        }
    },
    LOGOUT{
        {
            this.command = new CmdLogOut();
        }
    },
    ERROR{
        {
            this.command = new CmdError();
        }
    };

    ICommand command;
}
