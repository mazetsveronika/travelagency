package by.mazets.travelagency.entity;

import by.mazets.travelagency.entity.type.TransportType;
import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import by.mazets.travelagency.exception.TravelAgencyException;

import java.util.Date;

/**
 * Class {@code Voucher}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */

public class Voucher extends AbstractEntity {
    private String country;
    private Date dateFrom;
    private Date dateTo;
    private Tour tour;
    private TransportType transport;
    private Hotel hotel;

    public Voucher() {
    }

    public Voucher(int id, String country, Date dateFrom, Date dateTo, Tour tour, TransportType transport, Hotel hotel) {
        super(id);
        this.country = country;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.tour = tour;
        this.transport = transport;
        this.hotel = hotel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws TravelAgencyDataWrongException {
        if (country != null) {
            this.country = country;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect country value.");
        }
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) throws TravelAgencyDataWrongException {
        if (dateFrom != null) {
            this.dateFrom = dateFrom;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect dateFrom value.");
        }
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) throws TravelAgencyDataWrongException {
        if (dateTo != null) {
            this.dateTo = dateTo;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect dateTo value.");
        }
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) throws TravelAgencyDataWrongException {
        if (tour != null) {
            this.tour = tour;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect tour value.");
        }
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) throws TravelAgencyDataWrongException {
        if (transport != null) {
            this.transport = transport;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect transport value.");
        }
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) throws TravelAgencyDataWrongException {
        if (hotel != null) {
            this.hotel = hotel;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect hotel value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Voucher voucher = (Voucher) o;

        if (country != null ? !country.equals(voucher.country) : voucher.country != null) return false;
        if (dateFrom != null ? !dateFrom.equals(voucher.dateFrom) : voucher.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(voucher.dateTo) : voucher.dateTo != null) return false;
        if (tour != null ? !tour.equals(voucher.tour) : voucher.tour != null) return false;
        if (transport != voucher.transport) return false;
        return hotel != null ? hotel.equals(voucher.hotel) : voucher.hotel == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Voucher{");
        sb.append("country='").append(country).append('\'');
        sb.append("dateFrom='").append(dateFrom).append('\'');
        sb.append("dateTo='").append(dateTo).append('\'');
        sb.append(", tour='").append(tour).append('\'');
        sb.append(", transport='").append(transport).append('\'');
        sb.append(", hotel='").append(hotel).append('\'');
        return sb.toString();
    }
}

