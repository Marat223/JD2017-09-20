package by.it.shelkovich.project.java.controller.actions;

public enum Actions {
    LOGIN{
        {
            command = new CmdLogIn();
        }
    },
    LOGOUT{
        {
            command = new CmdLogOut();
        }
    },
    SIGNUP{
        {
            command = new CmdSignUp();
        }
    },
    ERROR{
        {
            command = new CmdError();
        }
    },
    MANAGE{
        {
            command = new CmdManage();
        }
    },
    PUBLISH{
        {
            command = new CmdPublish();
        }
    },
    MAIN{
        {
            command = new CmdMain();
        }
    };

    public ICommand command;
}
