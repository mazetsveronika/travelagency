package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.ServiceFactory;
import by.mazets.travelagency.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code ContinueRegisterCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ContinueRegisterCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start ContinueRegistrCommand");
        String page;

        HttpSession session = request.getSession(true);
        User newUser = (User) request.getSession().getAttribute("user");

        logger.debug("ContinueRegistrCommand new User: " + newUser);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        User user = null;

        try {
            user = userService.logIn(newUser.getLogin(), newUser.getPassword());
            logger.debug("ContinueRegisterCommand user: " + user);
            session.setAttribute("user", user);
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            session.setAttribute("surname", user.getSurname());
            session.setAttribute("money", user.getMoney());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("userRole", user.getRole());
            page = PageContainer.USER_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            logger.error("ContinueRegisterCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish ContinueRegisterCommand: " + user);
        return page;
    }
}
