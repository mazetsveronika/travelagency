package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code ToRegisterPageCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ToRegisterPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    public static final String TO_REGISTER = "toRegister";

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start ToRegisterPageCommand");

        String toRegister = request.getParameter(TO_REGISTER);
        request.setAttribute(TO_REGISTER, toRegister);

        logger.debug("finish ToRegisterPageCommand");
        return PageContainer.REGISTER_PAGE;
    }
}
