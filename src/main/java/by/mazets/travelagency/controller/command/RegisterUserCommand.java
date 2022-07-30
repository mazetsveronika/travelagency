package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.type.RoleType;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.ServiceFactory;
import by.mazets.travelagency.service.UserService;
import by.mazets.travelagency.service.validator.Validator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * Class {@code RegisterUserCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class RegisterUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start RegisterUserCommand");

        User user = null;
        String page;
        if (validate(request)) {
            logger.debug(request.getParameter("roleUser"));
            Validator validator = Validator.getInstance();

            try {
                if (validator.validateUniqueLogin(request.getParameter("login"))) {
                    user = new User();
                    user.setName(request.getParameter("name"));
                    user.setSurname(request.getParameter("surname"));
                    user.setMoney(BigDecimal.valueOf(Double.parseDouble(request.getParameter("money"))));
                    user.setEmail(request.getParameter("email"));
                    user.setLogin(request.getParameter("login"));
                    user.setPassword(request.getParameter("password"));
                    user.setRole(RoleType.valueOf(request.getParameter("userRole").toUpperCase()));

                    String role = request.getParameter("userRole");

                    ServiceFactory factory = ServiceFactory.getInstance();
                    UserService userService = factory.getUserService();

                    userService.create(user);
                    if (user != null) {
                        request.setAttribute("user", user);
                        request.setAttribute("name", user.getName());
                        request.getSession().setAttribute("user", user);
                    }
                    page = PageContainer.RESULT_REGISTER_PAGE;
                } else {
                    request.setAttribute("error", "Login is not unique. Please try again.");
                    page = PageContainer.ERROR_PAGE;
                }
            } catch (TravelAgencyServiceException e) {
                logger.error("RegisterUserCommand error.", e);
                page = PageContainer.ERROR_PAGE;
            } catch (TravelAgencyDataWrongException e) {
                logger.error("RegisterUserCommand error.", e);
                page = PageContainer.ERROR_PAGE;
            } catch (TravelAgencyDaoException e) {
                logger.error("RegisterUserCommand error.", e);
                page = PageContainer.ERROR_PAGE;
            }
        } else {
            request.setAttribute("error", "The data entered is not correct. Please try again.");
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish RegisterUserCommand" + user);
        return page;
    }

    /**
     * Validate entered parameters
     *
     * @param request
     * @return
     */
    private boolean validate(HttpServletRequest request) {
        logger.debug("start boolean validate");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Double money = Double.parseDouble(request.getParameter("money"));
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Validator validator = Validator.getInstance();

        boolean result = validator.validateNameOrSurname(name) &&
                validator.validateNameOrSurname(surname) &&
                validator.validateMoney(money) &&
                validator.validateEmail(email) &&
                validator.validateLoginOrPassword(login) &&
                validator.validateLoginOrPassword(password);

        logger.debug("finish boolean validate with: " + result);

        return result;
    }
}


