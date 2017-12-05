package controller;

public enum Actions {

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
    LOGOUT {
        {
            this.command = new CmdLogout();
        }
    },
    ERROR {
        {
            this.command = new CmdError();
        }
    },
    LOOKCATALOG {
        {
            this.command = new CmdLookCatalog();
        }
    },
    CHECKRESULTS {
        {
            this.command = new CmdCheckResults();
        }
    };

    ICommand command;
}
