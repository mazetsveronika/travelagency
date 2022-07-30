package by.mazets.travelagency.entity;

import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import by.mazets.travelagency.exception.TravelAgencyException;

import java.math.BigDecimal;

/**
 * Class {@code Hotel}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */

public class Hotel extends AbstractEntity {

    private String name;
    private BigDecimal pricePerDay;

    public Hotel() {

    }

    public Hotel(int id, String name, BigDecimal pricePerDay) {
        super(id);
        this.name = name;
        this.pricePerDay = pricePerDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TravelAgencyDataWrongException {
        if (name != null && name != "") {
            this.name = name;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect name value.");
        }
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Hotel hotel = (Hotel) o;

        if (name != null ? !name.equals(hotel.name) : hotel.name != null) return false;
        return pricePerDay != null ? pricePerDay.equals(hotel.pricePerDay) : hotel.pricePerDay == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pricePerDay != null ? pricePerDay.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hotel{");
        sb.append("name='").append(name).append('\'');
        sb.append("pricePerDay='").append(pricePerDay).append('\'');
        return sb.toString();
    }
}
