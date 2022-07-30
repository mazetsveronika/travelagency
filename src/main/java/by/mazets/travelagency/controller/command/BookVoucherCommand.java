package by.mazets.travelagency.controller.command;

import by.mazets.travelagency.controller.Command;
import by.mazets.travelagency.controller.PageContainer;
import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Order;
import by.mazets.travelagency.entity.User;
import by.mazets.travelagency.entity.Voucher;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.OrderService;
import by.mazets.travelagency.service.ServiceFactory;
import by.mazets.travelagency.service.UserService;
import by.mazets.travelagency.service.VoucherService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class {@code BookVoucherCommand}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public class BookVoucherCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("start BookVoucherCommand");

        String page;

        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();
        VoucherService voucherService = factory.getVoucherService();
        UserService userService = factory.getUserService();

        String voucherID = request.getParameter("idvoucher");
        logger.debug("voucherID: " + voucherID);
        String userId = request.getParameter("id");
        logger.debug("userId: " + userId);
        List<AbstractEntity> vouchers;

        Order order = null;

        try {
            vouchers = voucherService.findAll();

            Voucher voucher = (Voucher) voucherService.findById(Integer.parseInt(voucherID));
            logger.debug("voucher: " + voucher);
            User user = (User) userService.findById(Integer.parseInt(userId));
            logger.debug("user: " + user);

            order = new Order();
            BigDecimal totalPrice = calculateTotalPrice(voucher, user);

            if (user.getMoney().compareTo(totalPrice) >= 0) {//?
                try {
                    order.setUser(user);
                    order.setVoucher(voucher);
                } catch (TravelAgencyDataWrongException e) {
                    e.printStackTrace();
                }
                orderService.create(order);

                logger.debug("order: " + order);

                request.setAttribute("vouchers", vouchers);

                request.setAttribute("userName", user.getName());
                request.setAttribute("countryOrder", order.getVoucher().getCountry());
                request.setAttribute("totalPrice", totalPrice);

                page = PageContainer.FINISH_ORDER;

            } else {
                request.setAttribute("notEnoughMoneyMessage", "Sorry, not enough money to pay.");
                page = PageContainer.USER_MENU_PAGE;
            }

        } catch (TravelAgencyDaoException e) {
            logger.error("BookVoucherCommand error.", e);
            page = PageContainer.ERROR_PAGE;

        } catch (TravelAgencyServiceException e) {
            logger.error("BookVoucherCommand error.", e);
            page = PageContainer.ERROR_PAGE;

        } catch (NumberFormatException e) {
            logger.error("BookVoucherCommand error.", e);
            request.setAttribute("error", "Create order, please.");
            page = PageContainer.ERROR_PAGE;
        }
        logger.debug("finish BookVoucherCommand: " + order);
        return page;
    }

    /**
     *
     * @param voucher
     * @param user
     * @return
     */
    private BigDecimal calculateTotalPrice(Voucher voucher, User user) {
        int nights = (int)(voucher.getDateTo().getTime() - voucher.getDateFrom().getTime())/(24 * 60 * 60 * 1000);
        BigDecimal totalPrice = (voucher.getHotel().getPricePerDay().add(voucher.getTour().getPrice())).
                multiply(BigDecimal.valueOf(nights)).
                multiply(BigDecimal.valueOf(((100 - user.getDiscount()))/100));
        return totalPrice;
    }
}




