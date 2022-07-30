package by.mazets.travelagency.controller.command;

import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.OrderService;
import by.mazets.travelagency.service.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class {@code RegisterUserCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ShowOrderByUserId implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start ShowOrderByUserID");

        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getId();
        logger.debug("for user: " + user);

        String page;

        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();

        try {
            List<AbstractEntity> ordersByUserId = orderService.findByUserId(id);
            request.setAttribute("ordersByUserId", ordersByUserId);
            if (ordersByUserId.isEmpty()) {
                request.setAttribute("errorUserMenu", "You have no orders yet.");
            }
            page = PageContainer.USER_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            logger.error("ShowOrderByUserID error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish ShowOrderByUserID");
        return page;
    }
}