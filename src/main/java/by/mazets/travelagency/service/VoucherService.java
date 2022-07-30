package by.mazets.travelagency.service;


import by.mazets.travelagency.entity.Voucher;
import by.mazets.travelagency.exception.TravelAgencyServiceException;

import java.util.List;
/**
 * Interface {@code VoucherService}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public interface VoucherService extends EntityService {

    List<Voucher> getVouchersByCountry(String country) throws TravelAgencyServiceException;
    List<Voucher> getVouchersByTourType(String type) throws TravelAgencyServiceException;

}
