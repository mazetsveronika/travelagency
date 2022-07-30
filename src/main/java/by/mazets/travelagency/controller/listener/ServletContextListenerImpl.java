package by.mazets.travelagency.controller.listener;

import by.mazets.travelagency.connection.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
      //  ConnectionPool.getInstance();
        logger.log(Level.INFO,"++++++++++ contextInitialized : " +sce.getServletContext().getServerInfo());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO,"---------- contextDestroyed : " +sce.getServletContext().getContextPath());

    }


}
