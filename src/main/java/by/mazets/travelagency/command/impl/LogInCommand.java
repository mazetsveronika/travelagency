package by.mazets.travelagency.command.impl;


import by.mazets.travelagency.command.Command;
import by.mazets.travelagency.command.PagePath;
import by.mazets.travelagency.command.Router;
import by.mazets.travelagency.command.SessionAttribute;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.UserService;
import by.mazets.travelagency.service.impl.UserServiceImpl;
import by.mazets.travelagency.util.PhoneNumberFormatter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.mazets.travelagency.command.RequestAttribute.*;
import static by.mazets.travelagency.command.RequestParameter.*;

public class LogInCommand implements Command {
    
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        try {
            Optional<User> user = userService.findUser(login, password);
            if (user.isPresent()) {
                ServletContext servletContext = request.getServletContext();
                servletContext.setAttribute(login, user.get().getName());
                String number = PhoneNumberFormatter.format(user.get().getPhoneNumber());
                session.setAttribute(SessionAttribute.NUMBER, number);
                session.setAttribute(SessionAttribute.USER, user.get());
                session.setAttribute(SessionAttribute.ROLE, user.get().getRole());
                return new Router(PagePath.INDEX_PAGE, Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(USER_LOGIN, login);
                request.setAttribute(USER_PASSWORD, password);
                return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
            }
        } catch (TravelAgencyServiceException e) {
            logger.error("Error has occurred while signing in: " + e);
            return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
        }
    }
}
