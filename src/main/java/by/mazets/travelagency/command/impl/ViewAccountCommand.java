package by.mazets.travelagency.command.impl;

import by.mazets.travelagency.command.Command;
import by.mazets.travelagency.command.PagePath;
import by.mazets.travelagency.command.Router;
import by.mazets.travelagency.command.SessionAttribute;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.UserService;
import by.mazets.travelagency.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.mazets.travelagency.command.SessionAttribute.PICTURE;

public class ViewAccountCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();



    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String login = user.getLogin();
        try {
            Optional<String> picture = userService.loadPicture(login);
            picture.ifPresent(s -> request.setAttribute(PICTURE, s));
            return new Router(PagePath.ACCOUNT_PAGE, Router.RouterType.FORWARD);
        } catch (TravelAgencyServiceException e) {
            logger.error("Error has occurred while redirecting to account page: " + e);
            return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
        }
    }
}