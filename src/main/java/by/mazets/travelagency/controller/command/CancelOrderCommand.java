package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.entity.Order;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.OrderService;
import by.mazets.travelagency.service.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code CancelOrderCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class CancelOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start CancelOrderCommand");

        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();

        String orderID = request.getParameter("myOrderId");
        logger.debug("orderID: " + orderID);

        try {
            Order order = (Order) orderService.findById(Integer.parseInt(orderID));
            logger.debug("cancel order: " + order);
            if (order != null) {
                orderService.cancelOrder(order);
            } else {
                request.setAttribute("errorUserMenu", "Order not found.");
            }
            page = PageContainer.USER_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            logger.error("CancelOrderCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        } catch (NumberFormatException e) {
            logger.error("CancelOrderCommand error.", e);
            request.setAttribute("error", "Select order to cancel");
            page = PageContainer.ERROR_PAGE;
        }

        logger.debug("finish CancelOrderCommand");
        return page;
    }
}
