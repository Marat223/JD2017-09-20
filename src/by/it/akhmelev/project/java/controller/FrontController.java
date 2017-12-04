package by.it.akhmelev.project.java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICommand cmd=new ActionFactory().getCommand(req);
        cmd.execute(req);
        RequestDispatcher disp=req.getRequestDispatcher(cmd.getJsp());
        disp.include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICommand cmd=new ActionFactory().getCommand(req);
        ICommand nextAction=cmd.execute(req);
        if (nextAction==null) {
            RequestDispatcher disp=req.getRequestDispatcher(cmd.getJsp());
            disp.forward(req,resp);}
        else
        /* TODO fix fo */
            resp.sendRedirect("do?command="+nextAction);
    }
}
