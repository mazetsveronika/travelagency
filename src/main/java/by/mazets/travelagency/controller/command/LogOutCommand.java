package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code LogOutCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class LogOutCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start LogOutCommand");

        request.getSession().invalidate();

        logger.debug("finish LogOutCommand");
        return PageContainer.INDEX_PAGE;
    }
}
