package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Hotel;
import by.mazets.travelagency.entity.Tour;
import by.mazets.travelagency.entity.Voucher;
import by.mazets.travelagency.entity.type.TransportType;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.HotelService;
import by.mazets.travelagency.service.ServiceFactory;
import by.mazets.travelagency.service.TourService;
import by.mazets.travelagency.service.VoucherService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Class {@code AddVoucherCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class AddVoucherCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start AddVoucherCommand");
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        TourService tourService = factory.getTourService();
        HotelService hotelService = factory.getHotelService();
        VoucherService voucherService = factory.getVoucherService();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            List<AbstractEntity> tours = tourService.findAll();
            List<AbstractEntity> hotels = hotelService.findAll();
            request.setAttribute("tours", tours);
            logger.debug("tours " + tours);
            request.setAttribute("hotels", hotels);
            logger.debug("tours " + tours);

            String country = request.getParameter("countryVal");
            String dateFrom = request.getParameter("dateFromVal");
            String dateTo = request.getParameter("dateToVal");

            int idTour = Integer.parseInt(request.getParameter("idTour"));
            int idTransport = Integer.parseInt(request.getParameter("idTransport"));
            int idHotel = Integer.parseInt(request.getParameter("idHotel"));

            Voucher voucher = new Voucher();
            voucher.setCountry(country);
            voucher.setDateFrom(dateFormat.parse(dateFrom));
            voucher.setDateTo(dateFormat.parse(dateTo));
            voucher.setTour((Tour) tourService.findById(idTour));
            voucher.setTransport(TransportType.getValue(idTransport));
            voucher.setHotel((Hotel) hotelService.findById(idHotel));

            logger.debug("add voucher " + voucher);

            voucherService.create(voucher);
            request.setAttribute("acceptedMessageAdminAdd", "add voucher accepted");
            page = PageContainer.ADMIN_MENU_PAGE;

        } catch (TravelAgencyServiceException | TravelAgencyDaoException | TravelAgencyDataWrongException e) {
            logger.error("AddVoucherCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        } catch (ParseException | NumberFormatException e) {
            logger.error("AddVoucherCommand error.", e);
            request.setAttribute("error", "Incorrect data value. Please try again.");
            page = PageContainer.ERROR_PAGE;
        }

        logger.debug("finish AddVoucherCommand");
        return page;
    }
}


