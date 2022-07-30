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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code ToChooseVoucherCommand} allows to see available vouchers
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class ToChooseVoucherCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start ToChooseVoucherCommand");

        String page;

        ServiceFactory factory = ServiceFactory.getInstance();
        VoucherService voucherService = factory.getVoucherService();
        OrderService orderService = factory.getOrderService();

        List<AbstractEntity> vouchers;
        List<BigDecimal> voucherPrice = new ArrayList<>();

        try {
            List<AbstractEntity> orders = orderService.findAll();
            vouchers = voucherService.findAll();
            Order order;

            //not show not available to order vouchers//fixme
            for (int i = 0; i < vouchers.size(); i++) {
                for (int j = 0; j < orders.size(); j++) {
                    order = (Order) orders.get(j);
                    if (vouchers.get(i).getId() == order.getVoucher().getId()) {
                        vouchers.remove(i);
                    }
                }
            }
            //calculate voucher total price and create array//fixme
          for (int i = 0; i < vouchers.size(); i++) {
                voucherPrice.add(calculateVoucherTotalPrice((Voucher) vouchers.get(i)));
            }

            if (!vouchers.isEmpty()) {
                request.setAttribute("vouchers", vouchers);
                request.setAttribute("voucherPrice", voucherPrice);
            } else {
                request.setAttribute("errorUserMenu", "Vouchers not found");
            }
            request.setAttribute("vouchers", vouchers);
            page = PageContainer.USER_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            logger.error("LogInCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }

        logger.debug("start ToChooseVoucherCommand");
        return page;
    }

    private BigDecimal calculateVoucherTotalPrice(Voucher voucher) {
        int nights = (int)(voucher.getDateTo().getTime() - voucher.getDateFrom().getTime())/(24 * 60 * 60 * 1000);
        BigDecimal totalPrice = (voucher.getHotel().getPricePerDay().add(voucher.getTour().getPrice())).
                multiply(BigDecimal.valueOf(nights));
        return totalPrice;
    }

  }





