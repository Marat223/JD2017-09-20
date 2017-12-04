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
    RESETDB{
        {
            this.command = new CmdResetDB();
        }
    },
    LOGOUT{
        {
            this.command = new CmdLogout();
        }
    },
//    CREATEAD{
//        {
//            this.command = new CmdCreateAD();
//        }
//    },
    ERROR{
        {
            this.command = new CmdError();
        }
    };


    ICommand command;
}
