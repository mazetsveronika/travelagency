package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.ServiceFactory;
import by.mazets.travelagency.service.TourService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code UpdateTourCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class UpdateTourCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start UpdateTourCommand");
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        TourService tourService = factory.getTourService();

        try {
            int idTour = Integer.parseInt(request.getParameter("idTour"));
            boolean isHot = Boolean.parseBoolean(request.getParameter("isHot"));
            tourService.setHotTour(idTour, isHot);

            request.setAttribute("acceptedMessageAdminUpdate", "update tour accepted");
            page = PageContainer.ADMIN_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            logger.error("UpdateTourCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish UpdateTourCommand");
        return page;
    }
}
