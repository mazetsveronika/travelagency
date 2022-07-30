package by.mazets.travelagency.dao;

import by.mazets.travelagency.dao.impl.*;

/**
 * Class {@code DaoFactory} offers to get object references,
 * which class implements the required DAO interface
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */

public class DaoFactory {

    private final UserDao USER_DAO = new UserDaoImpl();
    private final VoucherDao VOUCHER_DAO = new VoucherDaoImpl();
    private final TourDao TOUR_DAO = new TourDaoImpl();
    private final HotelDao HOTEL_DAO = new HotelDaoImpl();
    private final OrderDao ORDER_DAO = new OrderDaoImpl();

    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return USER_DAO;
    }

    public VoucherDao getVoucherDao() {
        return VOUCHER_DAO;
    }

    public TourDao getTourDao() {
        return TOUR_DAO;
    }

    public HotelDao getHotelDao() {
        return HOTEL_DAO;
    }

    public OrderDao getOrderDao() {
        return ORDER_DAO;
    }

}