package by.mazets.travelagency.dao;

import by.mazets.travelagency.entity.Voucher;
import by.mazets.travelagency.exception.TravelAgencyDaoException;

import java.util.List;

/**
 * Interface {@code VoucherDao}
 *
 * @author Veronika Mazets
 * @version 1.0 29/07/2022
 */
public interface VoucherDao extends EntityDao {

    List<Voucher> getVouchersByCountry(String country) throws TravelAgencyDaoException;
    List<Voucher> getVouchersByTourType(String type) throws TravelAgencyDaoException;
}