package by.mazets.travelagency.service.impl;


import by.mazets.travelagency.dao.DaoFactory;
import by.mazets.travelagency.dao.VoucherDao;
import by.mazets.travelagency.entity.AbstractEntity;
import by.mazets.travelagency.entity.Voucher;
import by.mazets.travelagency.exception.TravelAgencyDaoException;
import by.mazets.travelagency.exception.TravelAgencyServiceException;
import by.mazets.travelagency.service.VoucherService;

import java.util.List;

/**
 * Class {@code VoucherServiceImpl}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public class VoucherServiceImpl implements VoucherService {

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private VoucherDao voucherDao = daoFactory.getVoucherDao();

    public VoucherServiceImpl() {
    }

    @Override
    public List<Voucher> getVouchersByCountry(String country) throws TravelAgencyServiceException {
        if (country != null) {
            try {
                List<Voucher> vouchers = voucherDao.getVouchersByCountry(country);
                return vouchers;
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public List<Voucher> getVouchersByTourType(String type) throws TravelAgencyServiceException {
        if (type != null) {
            try {
                List<Voucher> vouchers = voucherDao.getVouchersByTourType(type);
                return vouchers;
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void create(AbstractEntity abstractEntity) throws TravelAgencyServiceException {
        if (abstractEntity != null) {
            try {
                voucherDao.create(abstractEntity);
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void update(AbstractEntity abstractEntity) throws TravelAgencyServiceException {
        if (abstractEntity != null) {
            try {
                voucherDao.update(abstractEntity);
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                voucherDao.delete(id);
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public AbstractEntity findById(int id) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                AbstractEntity vauher = voucherDao.findById(id);
                return vauher;
            } catch (TravelAgencyDaoException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public List<AbstractEntity> findAll() throws TravelAgencyServiceException {
        try {
            List<AbstractEntity> vouchers = voucherDao.findAll();
            return vouchers;
        } catch (TravelAgencyDaoException e) {
            throw new TravelAgencyServiceException(e);
        }
    }
}
