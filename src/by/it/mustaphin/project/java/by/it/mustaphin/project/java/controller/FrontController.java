package by.it.mustaphin.project.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICommand cmd = new ActionFactory().getCommand(req);
        ICommand nextAction = cmd.execute(req);
        if (null == nextAction) {
            req.getRequestDispatcher(cmd.getJsp()).forward(req, resp);
        } else {
            resp.sendRedirect("do?command=" + nextAction);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICommand cmd = new ActionFactory().getCommand(req);
        ICommand nextAction = cmd.execute(req);
        if (null == nextAction) {
            req.getRequestDispatcher(cmd.getJsp()).forward(req, resp);
        } else {
            resp.sendRedirect("do?command=" + nextAction);
        }
    }

}
