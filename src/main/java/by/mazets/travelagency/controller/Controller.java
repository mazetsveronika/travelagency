package by.mazets.travelagency.controller;

import java.io.*;

import by.mazets.travelagency.command.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet(name = "Controller", urlPatterns ={"/controller"})//?
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();


    public void init() {
        logger.debug("servlet init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandType = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandType.defineCommand(commandType);
        Router router = command.execute(request);
        switch (router.getType()) {
            case FORWARD -> request.getRequestDispatcher(router.getPage()).forward(request, response);
            case REDIRECT -> response.sendRedirect(request.getContextPath() + router.getPage());
            default -> request.getRequestDispatcher(PagePath.ERROR_404).forward(request, response);
        }
    }

    public void destroy() {
        logger.debug("servlet destroy");
        super.destroy();
    }
}
