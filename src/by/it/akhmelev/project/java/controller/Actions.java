package by.it.akhmelev.project.java.controller;

enum Actions {

    LOGIN {
        {
            this.command = new CmdLogin();
        }
    },
    SIGNUP {
        {
            this.command = new CmdSignup();
        }
    },
    LOGOUT{
        {
            this.command = new CmdLogout();
        }
    },
    ERROR{
        {
            this.command = new CmdError();
        }
    };


    ICommand command;
}
