package by.mazets.travelagency.controller.command;

import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code ToLoginPageCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ToLoginPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    public static final String TO_LOGIN = "toLogin";

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start ToLoginPageCommand");

        String toLogin = request.getParameter(TO_LOGIN);
        request.setAttribute(TO_LOGIN, toLogin);

        logger.debug("finish ToLoginPageCommand");
        return PageContainer.LOGIN_PAGE;
    }
}
