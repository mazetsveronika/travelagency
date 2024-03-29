package by.mazets.travelagency.controller.command;


import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Order;
import by.mazets.travelagency.entity.Voucher;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.OrderService;
import by.mazets.travelagency.service.ServiceFactory;
import by.mazets.travelagency.service.VoucherService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class {@code ViewVouchersByCountry}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ViewVouchersByCountry implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start ViewVouchersByCountry");

        List<Voucher> vouchers;
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        VoucherService voucherService = factory.getVoucherService();
        OrderService orderService = factory.getOrderService();

        String country = request.getParameter("country");

        try {
            vouchers = voucherService.getVouchersByCountry(country);
            List<AbstractEntity> orders = orderService.findAll();
            Order order;

            for (int i = 0; i < vouchers.size(); i++) {
                for (int j = 0; j < orders.size(); j++) {
                    order = (Order) orders.get(j);
                    if (vouchers.get(i).getId() == order.getVoucher().getId()) {
                        vouchers.remove(i);
                    }
                    if(vouchers.size() == 0) {
                        break;
                    }
                }
            }

            if (!vouchers.isEmpty()) {
                request.setAttribute("vouchers", vouchers);
            } else {
                request.setAttribute("error", "Vouchers not found");
            }
            page = PageContainer.VIEW_ALL_VOUCHERS;
        } catch (TravelAgencyServiceException e) {
            logger.error("ViewVouchersByCountry error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish ViewVouchersByCountry");
        return page;
    }
}
