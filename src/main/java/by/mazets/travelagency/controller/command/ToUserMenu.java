package by.mazets.travelagency.controller.command;

import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code ToUserMenu}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ToUserMenu implements Command {

    private static final Logger logger = LogManager.getLogger();
    public static final String TO_USER_MENU = "toUserMenu";

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start ToUserMenu");

        String toUserMenu = request.getParameter(TO_USER_MENU);
        request.setAttribute(TO_USER_MENU, toUserMenu);

        logger.debug("finish ToUserMenu");
        return PageContainer.USER_MENU_PAGE;
    }
}
