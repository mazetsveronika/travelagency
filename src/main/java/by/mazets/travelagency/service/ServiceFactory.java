package by.mazets.travelagency.service;

import by.mazets.travelagency.service.impl.*;

/**
 * Class {@code ServiceFactory} offers to get object references,
 * which class implements the required Service
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */

public class ServiceFactory {

    private final UserService USER_SERVICE = new UserServiceImpl();
    private final TourService TOUR_SERVICE = new TourServiceImpl();
    private final HotelService HOTEL_SERVICE = new HotelServiceImpl();
    private final VoucherService VOUCHER_SERVICE = new VoucherServiceImpl();
    private final OrderService ORDER_SERVICE = new OrderServiceImpl();

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public TourService getTourService() {
        return TOUR_SERVICE;
    }

    public HotelService getHotelService() {
        return HOTEL_SERVICE;
    }

    public VoucherService getVoucherService() {
        return VOUCHER_SERVICE;
    }

    public OrderService getOrderService() {
        return ORDER_SERVICE;
    }
}


