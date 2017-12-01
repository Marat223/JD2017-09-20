package by.it.ali.project.java.controller;


public enum Actions {
    LOGIN {
        {
            this.command = new CmdLogin();
        }
    },
    LOGOUT {
        {
            this.command = new CmdLogout();
        }
    },
    ERROR {
        {
            this.command = new CmdError();
        }
    };


    ICommand command;
}
