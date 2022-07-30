package by.mazets.travelagency.entity;

import by.mazets.travelagency.exception.TravelAgencyDataWrongException;
import by.mazets.travelagency.exception.TravelAgencyException;

import java.math.BigDecimal;

/**
 * Class {@code Tour}
 *
 * @author Veronika Mazets
 * @version 1.0 28/07/2022
 */
public class Tour extends AbstractEntity{

    private String type;
    private BigDecimal price;
    private boolean hot;

    public Tour() {
    }

    public Tour(int id, String type, BigDecimal price, boolean hot) {
        super(id);
        this.type = type;
        this.price = price;
        this.hot = hot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws TravelAgencyDataWrongException {
        if (type != null) {
            this.type = type;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect Tour Type value.");
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Tour tour = (Tour) o;

        if (hot != tour.hot) return false;
        if (type != null ? !type.equals(tour.type) : tour.type != null) return false;
        return price != null ? price.equals(tour.price) : tour.price == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (hot ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tour{");
        sb.append("Type='").append(type).append('\'');
        sb.append("Price='").append(price).append('\'');
        sb.append("hot='").append(hot).append('\'');
        return sb.toString();
    }
}
