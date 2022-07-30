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
 * Class {@code LogInCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class LogInCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start LogInCommand");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        HttpSession session = request.getSession(true);
        User user = null;
        try {
            user = userService.logIn(login, password);

            if (user != null) {
                request.setAttribute("user", user);
                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("name", user.getName());
                session.setAttribute("surname", user.getSurname());
                session.setAttribute("money", user.getMoney());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("login", user.getLogin());
                session.setAttribute("userRole", user.getRole());

                if (user.getRole().toString().equals("CLIENT")) {
                    page = PageContainer.USER_MENU_PAGE;
                } else if (user.getRole().toString().equals("ADMIN")) {
                    page = PageContainer.ADMIN_MENU_PAGE;
                }
            } else {
                session.setAttribute("error", "User not found. Create your account, please.");
                page = PageContainer.ERROR_PAGE;
            }
        } catch (TravelAgencyServiceException e) {
            logger.error("LogInCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish LogInCommand: " + user);
        return page;
    }
}
