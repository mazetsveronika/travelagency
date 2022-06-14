package by.mazets.travelagency.service;

import by.mazets.travelagency.entity.Voucher;
import by.mazets.travelagency.exception.TravelAgencyServiceException;

import java.util.List;

public interface VoucherService  {

    List<Voucher> getVouchersByCountry(String country) throws TravelAgencyServiceException;
    List<Voucher> getVouchersByTourType(String type) throws TravelAgencyServiceException;

}