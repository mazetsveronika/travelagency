package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.exception.TravelAgencyCommandException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.ServiceFactory;
import by.mazets.travelagency.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * Class {@code UpdateBalanceCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class UpdateBalanceCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start UpdateBalanceCommand");

        String page;
        HttpSession session = request.getSession(true);
        User currentUser = (User) request.getSession().getAttribute("user");
        int userId = currentUser.getId();

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        BigDecimal newBalance;

        try {
            try {
                newBalance = BigDecimal.valueOf(Double.parseDouble(request.getParameter("newBalance")));
                session.setAttribute("usermoney", newBalance);

            } catch (NumberFormatException e) {
                logger.error("TravelAgencyCommandException error.", e);
                throw new TravelAgencyCommandException();
            }

            if (newBalance.compareTo(newBalance) >= 0) {
                userService.setMoney(userId, newBalance);
                request.setAttribute("acceptedMessage", "accepted");
                page = PageContainer.ACCOUNT_PAGE;
            } else {
                logger.error("Incorrect balance value.");
                request.setAttribute("error", "Incorrect balance value.");
                page = PageContainer.ERROR_PAGE;
            }
        } catch (TravelAgencyServiceException | TravelAgencyCommandException e) {
            logger.error("TravelAgencyServiceException error.", e);
            request.setAttribute("error", "Incorrect balance value.");
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish UpdateBalanceCommand");
        return page;
    }
}
