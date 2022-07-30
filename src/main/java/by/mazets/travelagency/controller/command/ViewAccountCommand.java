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
 * Class {@code ViewAccountCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ViewAccountCommand implements Command {

    private static final Logger logger = LogManager.getLogger();



    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start ViewAccountCommand");
        String page;
        HttpSession session = request.getSession(true);
        User currentUser = (User) request.getSession().getAttribute("user");
        int id = currentUser.getId();

        logger.debug("ViewAccountCommand user: " + currentUser);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        try {
            User user = (User) userService.findById(id);
            if (user != null) {
                request.setAttribute("user", user);
                session.setAttribute("user", user);
                session.setAttribute("username", user.getName());
                session.setAttribute("usersurname", user.getSurname());
                session.setAttribute("usermoney", user.getMoney());
                session.setAttribute("useremail", user.getEmail());
                session.setAttribute("userdiscount", user.getDiscount());
                session.setAttribute("userlogin", user.getLogin());



                page = PageContainer.ACCOUNT_PAGE;
            } else {
                logger.error("User not found.");
                session.setAttribute("error", "User not found.");
                page = PageContainer.ERROR_PAGE;
            }

        } catch (TravelAgencyServiceException e) {
            logger.error("ViewAccountCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish ViewAccountCommand");
        return page;
    }



    }
