package by.it.akhmelev.project.java.controller;

import javax.servlet.http.HttpServletRequest;

interface ICommand {

    ICommand execute(HttpServletRequest req);
    String getJsp();

}
