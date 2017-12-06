package by.it.mustaphin.project.java.controller;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {

    ICommand execute(HttpServletRequest req);

    String getJsp();
}
