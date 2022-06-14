package by.mazets.travelagency.command.impl;

import by.mazets.travelagency.command.Command;
import by.mazets.travelagency.command.PagePath;
import by.mazets.travelagency.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToLoginPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    public static final String TO_LOGIN = "toLogin";

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("start ToLoginPageCommand");

        String toLogin = request.getParameter(TO_LOGIN);//todo
        request.setAttribute(TO_LOGIN, toLogin);//todo

        logger.debug("finish ToLoginPageCommand");
        return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
    }


}