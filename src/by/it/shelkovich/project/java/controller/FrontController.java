package by.it.shelkovich.project.java.controller;

import by.it.shelkovich.project.java.controller.actions.ActionFactory;
import by.it.shelkovich.project.java.controller.actions.Actions;
import by.it.shelkovich.project.java.controller.actions.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servGETnPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servGETnPost(req, resp);
    }

    protected void servGETnPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ICommand command = ActionFactory.INSTANCE.getCommand(req);
        ICommand nextCommand = command.execute();
        if (nextCommand==null) {
            RequestDispatcher disp=req.getRequestDispatcher(command.getJSP());
            disp.include(req,resp);}
        else{
            resp.sendRedirect(nextCommand.getJSP());
        }

    }
}
