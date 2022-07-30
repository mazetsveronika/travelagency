package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.ServiceFactory;
import by.mazets.travelagency.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code UpdateDiscountCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class UpdateDiscountCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start UpdateDiscountCommand");
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        try {
            logger.debug(request.getParameter("discountVal"));
            int idUser = Integer.parseInt(request.getParameter("idUser"));
            logger.debug(idUser);
            double discount = Double.parseDouble(request.getParameter("discountVal"));


            logger.debug(discount);
            userService.setDiscount(idUser, discount);

            request.setAttribute("acceptedMessageAdminDiscount", "update discount accepted");
            page = PageContainer.ADMIN_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            logger.error("UpdateDiscountCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish UpdateDiscountCommand");
        return page;
    }

}
