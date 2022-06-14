package by.mazets.travelagency.command.impl;

import by.mazets.travelagency.command.Command;
import by.mazets.travelagency.command.PagePath;
import by.mazets.travelagency.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ToUserMenu implements Command {

    private static final Logger logger = LogManager.getLogger();
    public static final String TO_USER_MENU = "toUserMenu";

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("start ToUserMenu");

        String toUserMenu = request.getParameter(TO_USER_MENU);//todo
        request.setAttribute(TO_USER_MENU, toUserMenu);//todo

        logger.debug("finish ToUserMenu");
        return new Router(PagePath.USER_MENU_PAGE, Router.RouterType.FORWARD);
    }
}

