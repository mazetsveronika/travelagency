package by.mazets.travelagency.controller;

import by.mazets.travelagency.connection.ConnectionPool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Class {@code Controller}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */

@WebServlet(urlPatterns = {"/Controller","*.do"})

public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    public static final String COMMAND = "command";


    public void init(){
        ConnectionPool.getInstance();
        logger.log(Level.INFO,"-------> Servlet Init : " + this.getServletInfo());


    }
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("start get method");
        processRequest(request, response);
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("start post method");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);

        Command command = CommandManager.getInstance().getCommand(commandName);
        logger.debug("command: " + command.getClass().getSimpleName());

        String page = command.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Controller error.", e);
            page = PageContainer.ERROR_PAGE;
            dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO,"-------> Servlet Destroyed : " + this.getServletName());
    }

}
